package by.valvik.banking.context;

import by.valvik.banking.domain.Client;

import java.util.HashMap;
import java.util.Map;

public class Holder {

    private final Map<String, String> attributes;

    private boolean isAuthorize;

    private boolean isExit;

    private Client client;

    private Holder() {

        attributes = new HashMap<>();

    }

    public void add(String name, String value) {

        attributes.put(name, value);

    }

    public String get(String name) {

        return attributes.getOrDefault(name, null);

    }

    public boolean isAuthorize() {

        return isAuthorize;

    }

    public void setAuthorize(boolean authorize) {

        isAuthorize = authorize;

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
