package banking.view.impl;

import banking.model.Model;
import banking.model.Params;
import banking.view.IPage;

public class InfoPage implements IPage {

    @Override
    public void display(Model model) {

        System.out.println(model.get(Params.MESSAGE));

        System.out.println();

    }

}
