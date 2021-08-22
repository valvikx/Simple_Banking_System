package by.valvik.banking.view.impl;

import by.valvik.banking.context.Holder;
import by.valvik.banking.constant.Param;
import by.valvik.banking.view.Page;

import java.util.Scanner;

public class IncomePage implements Page {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void display(Holder holder) {

        System.out.println("Enter income:");

        String income = scanner.nextLine().trim();

        holder.add(Param.INCOME, income);

    }

}
