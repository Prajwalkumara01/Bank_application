package com.bank;


import jakarta.persistence.*;

@Entity
@Table(name = "user_accounts")
public class UserAccount {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @Column(name = "name", nullable = false)
 private String name;

 @Column(name = "password", nullable = false)
 private String password;

 @Column(name = "mobile_number", nullable = false, unique = true)
 private String mobileNumber;

 @Column(name = "dob", nullable = false)
 private String dob;

 @Column(name = "balance", nullable = false)
 private Double balance;


 public Long getId() {
     return id;
 }

 public void setId(Long id) {
     this.id = id;
 }

 public String getName() {
     return name;
 }

 public void setName(String name) {
     this.name = name;
 }

 public String getPassword() {
     return password;
 }

 public void setPassword(String password) {
     this.password = password;
 }

 public String getMobileNumber() {
     return mobileNumber;
 }

 public void setMobileNumber(String mobileNumber) {
     this.mobileNumber = mobileNumber;
 }

 public String getDob() {
     return dob;
 }

 public void setDob(String dob) {
     this.dob = dob;
 }

 public Double getBalance() {
     return balance;
 }

 public void setBalance(Double balance) {
     this.balance = balance;
 }
}
