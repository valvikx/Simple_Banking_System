package banking.view;

import banking.view.impl.*;

public final class Pages {

    public static final IPage AUTHORIZATION_PAGE = new AuthorizationPage();

    public static final IPage CLIENT_PAGE = new ClientPage();

    public static final IPage CARD_PAGE = new CardPage();

    public static final IPage LOGIN_PAGE = new LoginPage();

    public static final IPage INFO_PAGE = new InfoPage();

    public static final IPage BALANCE_PAGE = new BalancePage();

    public static final IPage INCOME_PAGE = new IncomePage();

    public static final IPage TRANSFER_PAGE = new TransferPage();

    public static final IPage TRANSFER_MONEY_PAGE = new TransferMoneyPage();

    private Pages() {

    }

}
