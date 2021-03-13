package com.fundtransfer.dto;

public class TransferRequest {

  private Long amount;
  private String currency;
  private String originAccount;
  private String destinationAccount;
  private String description;

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getOriginAccount() {
    return originAccount;
  }

  public void setOriginAccount(String originAccount) {
    this.originAccount = originAccount;
  }

  public String getDestinationAccount() {
    return destinationAccount;
  }

  public void setDestinationAccount(String destinationAccount) {
    this.destinationAccount = destinationAccount;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
