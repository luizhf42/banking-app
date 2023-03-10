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
        System.out.println("\nMenu: ");
        System.out.println("1 - Show all accounts");
        System.out.println("2 - Show one account");
        System.out.println("3 - Create new account");
        System.out.println("4 - Make a deposit");
        System.out.println("5 - Withdraw money");
        System.out.println("6 - Transfer money");
        System.out.println("0 - Exit the ATM");
        System.out.print("Choose an option: ");
        final int chosenOption = scanner.nextInt();
        System.out.println();

        switch (chosenOption) {
            case 1 -> showAccounts();
            case 2 -> {
                int index = readAccountId("Insert the account ID: ") - 1;
                showAccountDetails(index);
            }
            case 3 -> createAccount();
            case 4 -> deposit();
            case 5 -> withdraw();
            case 6 -> transferMoney();
            case 0 -> System.exit(0);
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
        final Account account = accounts.get(index);
        System.out.println("Owner: " + account.getOwnerName());
        System.out.println("ID: " + account.getId());
        System.out.println("Actual balance: " + account.getBalance());
        System.out.println();
    }

    private void deposit() {
        final int accountIndex = getAccountIndex("Insert the account ID: ");
        System.out.print("Insert the amount you want to deposit: ");
        final long amount = scanner.nextLong();

        final Account account = accounts.get(accountIndex);
        final long actualBalance = account.getBalance();
        final long newBalance = actualBalance + amount;
        account.updateBalance(newBalance);
        System.out.printf("Success! Now your balance is %d \n", newBalance);
    }

    private void withdraw() {
        final int accountIndex = getAccountIndex("Insert the account ID: ");
        System.out.print("Insert the amount you want to withdraw: ");
        final long amount = scanner.nextLong();

        final Account account = accounts.get(accountIndex);
        final long actualBalance = account.getBalance();
        final long newBalance = actualBalance - amount;
        if (newBalance >= 0) {
            account.updateBalance(newBalance);
            System.out.printf("Success! Now your balance is %d\n", newBalance);
        } else System.out.println("Insufficient balance to withdraw!");
    }

    private void transferMoney() {
        final int firstAccountIndex = getAccountIndex("Insert the ID of the account sending the money: ");
        final int secondAccountIndex = getAccountIndex("Insert the ID of the account receiving the money: ");
        System.out.print("Insert the amount being transferred: ");
        final long amount = scanner.nextLong();

        final Account firstAccount = accounts.get(firstAccountIndex);
        Account secondAccount = accounts.get(secondAccountIndex);
        final long firstAccountBalance = firstAccount.getBalance();

        if (amount > firstAccountBalance) System.out.println("Insufficient balance!");
        else {
            firstAccount.updateBalance(firstAccountBalance - amount);
            secondAccount.updateBalance(secondAccount.getBalance() + amount);
            System.out.printf("Successfully transferred %d to %s! \n", amount, secondAccount.getOwnerName());
        }
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

    private int readAccountId(String message) {
        while (true) try {
            System.out.print(message);
            return scanner.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("Invalid ID!");
            scanner.next();
        }
    }

    private int getAccountIndex(String message) {
        int accountId;
        while (true) try {
            accountId = readAccountId(message);
            if (accountId > accounts.size() + 1) {
                throw new Exception();
            } else return accountId - 1;
        } catch (Exception exception) {
            System.out.println("Nonexistent account ID! Try again!");
        }
    }
}

