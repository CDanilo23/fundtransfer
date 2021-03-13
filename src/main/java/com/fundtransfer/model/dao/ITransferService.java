package com.fundtransfer.model.dao;

import com.fundtransfer.dto.TransferRequest;
import com.fundtransfer.dto.TransferResponse;
import sun.java2d.pipe.ValidatePipe;

public interface ITransferService {

    TransferResponse validateAttempts(TransferRequest transferRequest);

    TransferResponse validateFunds(TransferRequest transferRequest);
}
