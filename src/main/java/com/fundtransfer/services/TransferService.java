package com.fundtransfer.services;

import com.fundtransfer.dto.TransferRequest;
import com.fundtransfer.dto.TransferResponse;
import com.fundtransfer.model.dao.AccountRepository;
import com.fundtransfer.model.dao.AttemptRepository;
import com.fundtransfer.model.dao.ITransferService;
import com.fundtransfer.model.entity.AccountEntity;
import com.fundtransfer.model.entity.AttemptEntity;
import com.sun.org.apache.bcel.internal.generic.NEW;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService implements ITransferService {


  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private AttemptRepository attemptRepository;

  @Override
  public TransferResponse validateAttempts(TransferRequest transferRequest) {

    TransferResponse transferResponse = null;
    LocalDate currentDate = LocalDate.now();
    AttemptEntity attemptEntity = attemptRepository.findAttemptByOriginAccountAndCurrentDate(transferRequest.getOriginAccount());
    System.out.println(currentDate.toString());
    if(Objects.nonNull(attemptEntity)){
        if(attemptEntity.getLocalDate().isEqual(currentDate)){

          //TO DO regresar response error
        }
    }{

    }

    return null;
  }

  @Override
  public TransferResponse validateFunds(TransferRequest transferRequest) {
    AccountEntity origenAccount = accountRepository.findByNumAccount(transferRequest.getOriginAccount());
    Long currentBalance = origenAccount.getAccountBalance() - transferRequest.getAmount();
    if(currentBalance>0){

      origenAccount.setAccountBalance(currentBalance);
      accountRepository.save(origenAccount);

      AccountEntity destinationAccount = accountRepository.findByNumAccount(transferRequest.getDestinationAccount());
      Long currentDestinationBalance = destinationAccount.getAccountBalance() + transferRequest.getAmount();
      destinationAccount.setAccountBalance(currentDestinationBalance);
      accountRepository.save(destinationAccount);
    }else{
      //TO DO retornar status OK
    }
    return null;
  }
}
