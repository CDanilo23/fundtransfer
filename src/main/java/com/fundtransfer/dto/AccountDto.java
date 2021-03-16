package com.fundtransfer.dto;

import java.util.List;

public class AccountDto {

  public static class Builder {

    private String status;
    private List<String> errors;
    private Long accountBalance;

    public Builder(String status) {
      this.status = status;
    }

    public Builder withErrors(List<String> errors) {
      this.errors = errors;
      return this;
    }

    public Builder withAccountBalance(Long accountBalance) {
      this.accountBalance = accountBalance;
      return this;
    }

    public AccountDto build() {
      AccountDto accountDto = new AccountDto();
      accountDto.status = this.status;
      accountDto.errors = this.errors;
      accountDto.accountBalance = accountBalance;
      return accountDto;
    }
  }

  private String status;
  private List<String> errors;
  private Long accountBalance;

  public Long getAccountBalance() {
    return accountBalance;
  }

  public void setAccountBalance(Long accountBalance) {
    this.accountBalance = accountBalance;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<String> getErrors() {
    return errors;
  }

  public void setErrors(List<String> errors) {
    this.errors = errors;
  }

}
