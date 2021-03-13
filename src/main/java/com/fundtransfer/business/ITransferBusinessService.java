package com.fundtransfer.business;

import com.fundtransfer.dto.TransferRequest;
import com.fundtransfer.dto.TransferResponse;

public interface ITransferBusinessService {
    TransferResponse transferFund(TransferRequest transferRequest);
}
