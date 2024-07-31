package com.practice;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



    class Account {
        private String accountNumber;
        private String pin;
        private double balance;

        public Account(String accountNumber, String pin, double initialBalance) {
            this.accountNumber = accountNumber;
            this.pin = pin;
            this.balance = initialBalance;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public boolean validatePin(String inputPin) {
            return pin.equals(inputPin);
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposit successful. New balance: " + balance);
            } else {
                System.out.println("Invalid deposit amount.");
            }
        }

        public void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println("Withdrawal successful. New balance: " + balance);
            } else {
                System.out.println("Invalid withdrawal amount or insufficient funds.");
            }
        }
    }

    class Bank {
        private Map<String, Account> accounts;

        public Bank() {
            accounts = new HashMap<>();
        }

        public void addAccount(Account account) {
            accounts.put(account.getAccountNumber(), account);
        }

        public Account getAccount(String accountNumber) {
            return accounts.get(accountNumber);
        }
    }

    public class ATM {
        private Bank bank;
        private Scanner scanner;

        public ATM() {
            bank = new Bank();
            scanner = new Scanner(System.in);

            // Adding some sample accounts
            bank.addAccount(new Account("12345", "1234", 1000.0));
            bank.addAccount(new Account("67890", "5678", 500.0));
        }

        public void start() {
            while (true) {
                System.out.println("Welcome to the ATM");
                System.out.print("Enter your account number: ");
                String accountNumber = scanner.nextLine();
                Account account = bank.getAccount(accountNumber);

                if (account != null) {
                    System.out.print("Enter your PIN: ");
                    String pin = scanner.nextLine();

                    if (account.validatePin(pin)) {
                        showMenu(account);
                    } else {
                        System.out.println("Invalid PIN. Try again.");
                    }
                } else {
                    System.out.println("Invalid account number. Try again.");
                }
            }
        }

        private void showMenu(Account account) {
            while (true) {
                System.out.println("\nATM Menu:");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("Current balance: " + account.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = Double.parseDouble(scanner.nextLine());
                        account.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawalAmount = Double.parseDouble(scanner.nextLine());
                        account.withdraw(withdrawalAmount);
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
        }

        public static void main(String[] args) {
            ATM atm = new ATM();
            atm.start();
        }
    }


