package com.fundtransfer.controller;

import com.fundtransfer.business.ITransferBusinessService;
import com.fundtransfer.dto.AccountDto;
import com.fundtransfer.dto.TransferRequest;
import com.fundtransfer.dto.TransferResponse;
import com.fundtransfer.model.dao.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class TransferController {

  @Autowired
  ITransferBusinessService transferBusinessService;

  @PostMapping(value = "/transfer" , produces = MediaType.APPLICATION_JSON_VALUE)
  public TransferResponse transfer(@RequestBody TransferRequest transferRequest) {
    return transferBusinessService.transferFund(transferRequest);
  }

  @GetMapping(value = "/account/{account}" , produces = MediaType.APPLICATION_JSON_VALUE)
  public AccountDto validateWhetherExistsAccount(@PathVariable String account) {
    return transferBusinessService.validateAccount(account);
  }

}
