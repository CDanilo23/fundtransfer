package com.fundtransfer.dto;

import java.util.List;

public class TransferResponse {

    private String status;
    private List<String> errors;
    private Long taxCollected;

  public String getCad() {
    return cad;
  }

  public void setCad(String cad) {
    this.cad = cad;
  }

  private String cad;


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
