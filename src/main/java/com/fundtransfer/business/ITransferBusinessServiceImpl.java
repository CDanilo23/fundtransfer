package com.fundtransfer.business;

import com.fundtransfer.dto.TransferRequest;
import com.fundtransfer.dto.TransferResponse;
import com.fundtransfer.model.dao.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ITransferBusinessServiceImpl implements ITransferBusinessService{

  @Autowired
  private ITransferService transferService;

  @Override
  public TransferResponse transferFund(TransferRequest transferRequest) {
    transferService.validateAttempts(transferRequest);
    return null;
  }
}
