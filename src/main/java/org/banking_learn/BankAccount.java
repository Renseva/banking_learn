package org.banking_learn;

import java.math.BigDecimal;
import java.util.Scanner;

public class BankAccount {
    private BigDecimal balance;
    public String accountNumber;
    public String accountName;
    public String userLogin;
    private String userPasswd;

    public BankAccount() {
    }

    public BankAccount(BigDecimal balance, String accountNumber, String accountName, String userLogin, String userPasswd) {
        this.balance = balance;
        this.accountNumber=accountNumber;
        this.accountName=accountName;
        this.userLogin=userLogin;
        this.userPasswd=userPasswd;
    }

    //method to deposit to bank account
    public BigDecimal deposit(BigDecimal amount) {
        return balance = balance.add(amount);
    }

    //method to withdraw from bank account
    public BigDecimal withdraw(BigDecimal amount) {
        if (balance.compareTo(amount) >= 0) {
            return balance = balance.subtract(amount);
        } else {
            System.out.println("You can only withdraw " + balance + ". " + balance + " has been withdrawn.");
            return balance = BigDecimal.valueOf(0);
        }
    }

    //method to print current balance
    public void printBalance() {
        System.out.println("Your balance is " + balance);
    }

    //method to transfer from one bank account to another
    public BigDecimal makeTransfer(BigDecimal amount,
                                   String receivingAccountNumber,
                                   Boolean credentialCheck) {
        BigDecimal initialBalance = balance;
        if (amount.compareTo(balance) <= 0 && credentialCheck) {
            balance = this.withdraw(amount);
            System.out.println(amount + " has been transferred to account " +
                    "number " + receivingAccountNumber + ".");

            //TODO: code to implement transfer once another account is there
        } else if (!credentialCheck) {
            System.out.println("Transaction failed. Please check transfer " +
                    "details are correct.");

        } else {
            balance = this.withdraw(balance);
            System.out.println("You don't have sufficient amount in your bank" +
                    " account and have not signed for overdraft facility. " +
                    "Only " + initialBalance + " was transferred to your " +
                    "chosen account.");

        }
               return balance;
    }


    //Main method
    public static void main(String[] args) {
        //create several accounts
        BankAccount bankAccount1 = new BankAccount(new BigDecimal(2000),
                "1234123412341234", "Lala Lalala", "lala", "444");
        //basic actions for now
        Scanner scanner = new Scanner(System.in);
        BigDecimal currentBalance = bankAccount1.balance;
        System.out.println("Your current balance is " + currentBalance + ". " +
                "\n Enter D to deposit or W to withdraw or T to transfer to " +
                "another account: ");
        String transactionChoice = scanner.nextLine();
        //TODO: refactor below
        if (transactionChoice.equalsIgnoreCase("d")) {
            System.out.println("Enter amount you want to deposit: ");
            BigDecimal amount = new BigDecimal(scanner.nextLong());
            currentBalance = bankAccount1.deposit(amount);

        } else if (transactionChoice.equalsIgnoreCase("w")) {
            System.out.println("Enter amount you want to withdraw: ");
            BigDecimal amount = new BigDecimal(scanner.nextLong());
            currentBalance = bankAccount1.withdraw(amount);

        } else if (transactionChoice.equalsIgnoreCase("t")) {
            System.out.println("Enter amount you want to transfer: ");
            BigDecimal amount = new BigDecimal(scanner.nextLong());
            currentBalance=bankAccount1.makeTransfer(amount, "1234 5678 1234 5678", true);

        }
        System.out.println("Your current account balance is " + currentBalance + ".");
    }
}