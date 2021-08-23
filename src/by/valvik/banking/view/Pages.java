package by.valvik.banking.view;

import by.valvik.banking.view.impl.*;

public enum Pages {

    USER(new UserPage()),
    CLIENT(new ClientPage()),
    LOGIN(new LoginPage()),
    INFO(new InfoPage()),
    INCOME(new IncomePage()),
    TRANSFER_CARD(new TransferCardPage()),
    TRANSFER_MONEY(new TransferMoneyPage());

    private final Page page;

    Pages(Page page) {

        this.page = page;

    }

    public Page getPage() {

        return page;

    }

}
