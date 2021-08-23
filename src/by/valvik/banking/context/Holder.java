package by.valvik.banking.context;

import by.valvik.banking.constant.Param;
import by.valvik.banking.domain.Client;

import java.util.HashMap;
import java.util.Map;

public class Holder {

    private final Map<Param, String> attributes;

    private boolean isExit;

    private Client client;

    public Holder() {

        attributes = new HashMap<>();

    }

    public void add(Param param, String value) {

        attributes.put(param, value);

    }

    public String get(Param param) {

        return attributes.get(param);

    }

    public boolean isExit() {

        return isExit;

    }

    public void setExit(boolean exit) {

        isExit = exit;

    }

    public Client getClient() {

        return client;

    }

    public void setClient(Client client) {

        this.client = client;

    }

}
