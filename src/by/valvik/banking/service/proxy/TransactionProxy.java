package by.valvik.banking.service.proxy;

import by.valvik.banking.exception.AppConfigurationException;
import by.valvik.banking.exception.ServiceException;
import by.valvik.banking.service.TransactionService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

import static java.lang.reflect.Proxy.newProxyInstance;

public class TransactionProxy {

    private static final TransactionProxy INSTANCE = new TransactionProxy();

    private TransactionProxy() {

    }

    public TransactionService getProxy(TransactionService transactionService, Connection connection) {

        return (TransactionService) newProxyInstance(TransactionProxy.class.getClassLoader(),
                                                     new Class<?>[] {TransactionService.class},
                                                     new TransactionInvocationHandler(transactionService, connection));


    }

    public static TransactionProxy getInstance() {

        return INSTANCE;

    }

    private static record TransactionInvocationHandler(TransactionService transactionService,
                                                       Connection connection) implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws ServiceException {

            Object result;

            try {

                if (connection.getAutoCommit()) {

                    connection.setAutoCommit(false);

                }

                result = method.invoke(transactionService, args);

                connection.commit();

            } catch (SQLException e) {

                try {

                    connection.rollback();

                    throw new ServiceException(e.getMessage());

                } catch (SQLException ex) {

                    throw new AppConfigurationException(ex);

                }

            } catch (InvocationTargetException | IllegalAccessException e) {

                throw new AppConfigurationException(e);

            }

            return result;

        }

    }

}
