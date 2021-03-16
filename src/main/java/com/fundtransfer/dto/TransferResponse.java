package com.fundtransfer.dto;

import java.math.BigDecimal;
import java.util.List;

public class TransferResponse {

  public static class Builder {

    private String status;
    private List<String> errors;
    private Long taxCollected;
    private BigDecimal CAD;

    public Builder(String status) {
      this.status = status;
    }

    public Builder withErrors(List<String> errors) {
      this.errors = errors;
      return this;
    }

    public Builder withTaxCollected(Long taxCollected) {
      this.taxCollected = taxCollected;
      return this;
    }

    public Builder withCAD(BigDecimal CAD) {
      this.CAD = CAD;
      return this;
    }

    public TransferResponse build() {
      TransferResponse transferResponse = new TransferResponse();
      transferResponse.status = this.status;
      transferResponse.errors = this.errors;
      transferResponse.taxCollected = this.taxCollected;
      transferResponse.CAD = this.CAD;
      return transferResponse;
    }

  }

  private String status;
  private List<String> errors;
  private Long taxCollected;
  private BigDecimal CAD;

  public BigDecimal getCAD() {
    return CAD;
  }

  public void setCAD(BigDecimal CAD) {
    this.CAD = CAD;
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

  public Long getTaxCollected() {
    return taxCollected;
  }

  public void setTaxCollected(Long taxCollected) {
    this.taxCollected = taxCollected;
  }

}








