package com.luizhf42.bank;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ATM {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Account> accounts = new ArrayList<>();

    public void initialize() {
        createAccount();
        while (true) try {
            showMenu();
        } catch (Exception exception) {
            System.out.println("An error occurred!");
        }
    }

    public void showMenu() {
        System.out.println("Choose an option: ");
        System.out.println("1 - Show accounts");
        int chosenOption = scanner.nextInt();
        switch (chosenOption) {
            case 1 -> showAccounts();
            case 5 -> System.exit(0);
            default -> System.out.println("Invalid option!");
        }
    }

    public void createAccount() {
        final Account newAccount = new Account(readAccountOwnerName(), readAccountId(), readAccountBalance());
        accounts.add(newAccount);
    }

    private void showAccounts() {
        System.out.println("\nExisting accounts:");
        for (int i = 0; i < accounts.size(); i++) {
            showAccountDetails(i);
        }
    }

    private void showAccountDetails(int index) {
        Account account = accounts.get(index);
        System.out.println("Owner: " + account.getOwnerName());
        System.out.println("ID: " + account.getId());
        System.out.println("Actual balance: " + account.getBalance());
        System.out.println();
    }


    private String readAccountOwnerName() {
        while (true) try {
            System.out.print("Enter the account owner name: ");
            return scanner.next();
        } catch (InputMismatchException exception) {
            System.out.println("Invalid name!");
            scanner.next();
        }
    }

    private int readAccountId() {
        while (true) try {
            System.out.print("Enter the account identifier (number): ");
            return scanner.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("Invalid ID!");
            scanner.next();
        }
    }

    private long readAccountBalance() {
        while (true) try {
            System.out.print("Enter the account initial balance: ");
            return scanner.nextLong();
        } catch (InputMismatchException exception) {
            System.out.println("Invalid balance!");
            scanner.next();
        }
    }
}

