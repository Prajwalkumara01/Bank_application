package com.bank;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
 public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);
     UserAccountService userAccountService = new UserAccountService();

     while (true) {
         System.out.println("1. Create User Account");
         System.out.println("2. Login");
         System.out.println("3. Exit");
         System.out.print("Choose an option: ");
         int option = getValidIntInput(scanner);

         switch (option) {
             case 1:
                 System.out.print("Enter name: ");
                 String name = scanner.next();
                 System.out.print("Enter password: ");
                 String password = scanner.next();
                 System.out.print("Enter mobile number: ");
                 String mobileNumber = scanner.next();
                 System.out.print("Enter date of birth (DD-MM-YYYY): ");
                 String dob = scanner.next();
                 System.out.print("Enter initial balance: ");
                 Double initialBalance = getValidDoubleInput(scanner);
                 userAccountService.createUserAccount(name, password, mobileNumber, dob, initialBalance);
                 System.out.println("User account created successfully.");
                 break;
             case 2:
                 System.out.print("Enter mobile number: ");
                 mobileNumber = scanner.next();
                 System.out.print("Enter password: ");
                 password = scanner.next();
                 UserAccount userAccount = userAccountService.login(mobileNumber, password);
                 if (userAccount != null) {
                     System.out.println("Login successful.");
                     while (true) {
                         System.out.println("1. Deposit");
                         System.out.println("2. Withdraw");
                         System.out.println("3. Check Balance");
                         System.out.println("4. Exit");
                         System.out.print("Choose an option: ");
                         int accountOption = getValidIntInput(scanner);

                         switch (accountOption) {
                             case 1:
                                 System.out.print("Enter amount to deposit: ");
                                 Double depositAmount = getValidDoubleInput(scanner);
                                 userAccountService.deposit(userAccount.getId(), depositAmount);
                                 System.out.println("Deposit successful.");
                                 break;
                             case 2:
                                 try {
                                     System.out.print("Enter amount to withdraw: ");
                                     Double withdrawAmount = getValidDoubleInput(scanner);
                                     userAccountService.withdraw(userAccount.getId(), withdrawAmount);
                                     System.out.println("Withdrawal successful.");
                                 } catch (Exception e) {
                                     System.out.println(e.getMessage());
                                 }
                                 break;
                             case 3:
                                 Double balance = userAccountService.checkBalance(userAccount.getId());
                                 System.out.println("Your current balance is: " + balance);
                                 break;
                             case 4:
                                 System.exit(0);
                                 break;
                             default:
                                 System.out.println("Invalid option.");
                         }
                     }
                 } else {
                     System.out.println("Invalid credentials.");
                 }
                 break;
             case 3:
                 System.exit(0);
                 break;
             default:
                 System.out.println("Invalid option.");
         }
     }
 }

 private static int getValidIntInput(Scanner scanner) {
     while (true) {
         try {
             return scanner.nextInt();
         } catch (InputMismatchException e) {
             System.out.println("Invalid input. Please enter a valid integer.");
             scanner.next(); 
         }
     }
 }

 private static Double getValidDoubleInput(Scanner scanner) {
     while (true) {
         try {
             return scanner.nextDouble();
         } catch (InputMismatchException e) {
             System.out.println("Invalid input. Please enter a valid number.");
             scanner.next();
         }
     }
 }
}
