package com.bank;


import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserAccountService {

 public void createUserAccount(String name, String password, String mobileNumber, String dob, Double initialBalance) {
     Session session = HibernateUtil.getSessionFactory().openSession();
     Transaction transaction = session.beginTransaction();

     UserAccount userAccount = new UserAccount();
     userAccount.setName(name);
     userAccount.setPassword(password); 
     userAccount.setMobileNumber(mobileNumber);
     userAccount.setDob(dob);
     userAccount.setBalance(initialBalance);

     session.save(userAccount);
     transaction.commit();
     session.close();
 }

 public UserAccount login(String mobileNumber, String password) {
     Session session = HibernateUtil.getSessionFactory().openSession();
     UserAccount userAccount = session.createQuery("FROM UserAccount ua WHERE ua.mobileNumber = :mobileNumber AND ua.password = :password", UserAccount.class)
             .setParameter("mobileNumber", mobileNumber)
             .setParameter("password", password)
             .uniqueResult();
     session.close();
     return userAccount;
 }

 public void deposit(Long userAccountId, Double amount) {
     Session session = HibernateUtil.getSessionFactory().openSession();
     Transaction transaction = session.beginTransaction();

     UserAccount userAccount = session.get(UserAccount.class, userAccountId);
     if (userAccount != null) {
         userAccount.setBalance(userAccount.getBalance() + amount);
         session.update(userAccount);
         transaction.commit();
     }
     
     session.close();
 }

 public void withdraw(Long userAccountId, Double amount) throws Exception {
     Session session = HibernateUtil.getSessionFactory().openSession();
     Transaction transaction = session.beginTransaction();

     UserAccount userAccount = session.get(UserAccount.class, userAccountId);
     if (userAccount != null && userAccount.getBalance() >= amount) {
         userAccount.setBalance(userAccount.getBalance() - amount);
         session.update(userAccount);
         transaction.commit();
     } else {
         throw new Exception("Insufficient balance or account not found.");
     }
     
     session.close();
 }

 public Double checkBalance(Long userAccountId) {
     Session session = HibernateUtil.getSessionFactory().openSession();
     UserAccount userAccount = session.get(UserAccount.class, userAccountId);
     Double balance = userAccount.getBalance();
     session.close();
     return balance;
 }
}
