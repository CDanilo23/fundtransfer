package com.fundtransfer.model.dao;

import com.fundtransfer.dto.AccountDto;
import com.fundtransfer.dto.TransferRequest;
import com.fundtransfer.dto.TransferResponse;

public interface ITransferService {

    TransferResponse validateFunds(TransferRequest transferRequest);

    AccountDto validateWhetherAccountExists(String account);
}
