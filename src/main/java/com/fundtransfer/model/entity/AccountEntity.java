package com.fundtransfer.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AccountEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String numAccount;
  private Long accountBalance;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getAccountBalance() {
    return accountBalance;
  }

  public void setAccountBalance(Long accountBalance) {
    this.accountBalance = accountBalance;
  }

  public String getNumAccount() {
    return numAccount;
  }

  public void setNumAccount(String numAccount) {
    this.numAccount = numAccount;
  }
}
