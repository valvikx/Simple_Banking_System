package by.valvik.banking.view;

import by.valvik.banking.view.impl.*;

public enum Pages {

    AUTHORIZATION(new UserPage()),
    CLIENT(new ClientPage()),
    CARD(new CardPage()),
    LOGIN(new LoginPage()),
    INFO(new InfoPage()),
    BALANCE(new BalancePage()),
    INCOME(new IncomePage()),
    TRANSFER(new TransferPage()),
    TRANSFER_MONEY_PAGE(new TransferMoneyPage());

    private final Page page;

    Pages(Page page) {

        this.page = page;

    }

    public Page getPage() {

        return page;

    }

}
