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
        System.out.println("Menu: ");
        System.out.println("1 - Show all accounts");
        System.out.println("2 - Show one account");
        System.out.println("3 - Create new account");
        System.out.println("4 - Make a deposit");
        System.out.println("6 - Exit the ATM");
        System.out.print("Choose an option: ");
        int chosenOption = scanner.nextInt();
        System.out.println();

        switch (chosenOption) {
            case 1 -> showAccounts();
            case 2 -> {
                int index = readAccountId() - 1;
                showAccountDetails(index);
            }
            case 3 -> createAccount();
            case 4 -> deposit();
            case 6 -> System.exit(0);
            default -> System.out.println("Invalid option!");
        }
    }

    public void createAccount() {
        final Account newAccount = new Account(readAccountOwnerName(), accounts.size() + 1, readAccountBalance());
        accounts.add(newAccount);
    }

    private void showAccounts() {
        System.out.println("Existing accounts:");
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

    private void deposit() {
        int accountId;
        while (true) try {
            accountId = readAccountId();
            if (accountId > accounts.size() + 1) {
                throw new Exception();
            } else break;
        } catch (Exception exception) {
            System.out.println("Nonexistent account ID! Try again!");
        }

        System.out.print("Insert the amount you want to deposit: ");
        long amount = scanner.nextLong();

        Account account = accounts.get(accountId - 1);
        account.updateBalance(account.getBalance() + amount);
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

    private long readAccountBalance() {
        while (true) try {
            System.out.print("Enter the account initial balance: ");
            return scanner.nextLong();
        } catch (InputMismatchException exception) {
            System.out.println("Invalid balance!");
            scanner.next();
        }
    }

    private int readAccountId() {
        while (true) try {
            System.out.print("Insert the account ID: ");
            return scanner.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("Invalid ID!");
            scanner.next();
        }
    }
}

