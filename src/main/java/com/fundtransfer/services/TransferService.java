package com.fundtransfer.services;

import com.fundtransfer.business.ITransferBusinessService;
import com.fundtransfer.dto.AccountDto;
import com.fundtransfer.dto.TransferRequest;
import com.fundtransfer.dto.TransferResponse;
import com.fundtransfer.helper.ApiCurrencyInvoker;
import com.fundtransfer.model.dao.AccountRepository;
import com.fundtransfer.model.dao.AttemptRepository;
import com.fundtransfer.model.dao.ITransferService;
import com.fundtransfer.model.entity.AccountEntity;
import com.fundtransfer.model.entity.AttemptEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TransferService implements ITransferService {

  Logger logger = Logger.getLogger(TransferService.class.getName());

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private AttemptRepository attemptRepository;

  @Autowired
  private ApiCurrencyInvoker apiCurrencyInvoker;

  @Value("${api.currency.cad}")
  private String otherCurrency;

  @Autowired
  private ITransferBusinessService transferBusinessService;

  BigDecimal cadCurrentTax = BigDecimal.ZERO;

  public boolean validateAttempts(TransferRequest transferRequest) {

    LocalDate currentDate = LocalDate.now();
    AttemptEntity attemptEntity = attemptRepository.findAttemptByOriginAccountAndCurrentDate(transferRequest.getOriginAccount());

    if(Objects.nonNull(attemptEntity)){
        if(attemptEntity.getLocalDate().isEqual(currentDate)){
          return false;
        }
    }

    AttemptEntity attemptEntityAvailable = attemptRepository
        .findAttemptByOriginAccount(transferRequest.getOriginAccount());
    Integer attempts = attemptEntityAvailable.getAttempts();
    attemptEntityAvailable.setAttempts(attempts + 1);
    attemptEntityAvailable.setLocalDate(LocalDate.now());
    attemptRepository.save(attemptEntityAvailable);
    return true;
  }

  @Override
  public TransferResponse validateFunds(TransferRequest transferRequest) {

    if (validateAttempts(transferRequest)) {

      AccountEntity origenAccount = accountRepository
          .findByNumAccount(transferRequest.getOriginAccount());

      Long currentBalance = origenAccount.getAccountBalance() - transferRequest.getAmount();
      if (currentBalance > 0) {

        Long currentTax = calculateTaxes(transferRequest.getAmount(),
            transferRequest.getCurrency());

        origenAccount.setAccountBalance(currentBalance - currentTax);
        accountRepository.save(origenAccount);

        AccountEntity destinationAccount = accountRepository
            .findByNumAccount(transferRequest.getDestinationAccount());
        Long currentDestinationBalance =
            destinationAccount.getAccountBalance() + transferRequest.getAmount();
        destinationAccount.setAccountBalance(currentDestinationBalance);
        accountRepository.save(destinationAccount);

        return new TransferResponse.Builder("OK").withTaxCollected(currentTax)
            .withCAD(cadCurrentTax).withErrors(new ArrayList<>()).build();

      } else {
        return new TransferResponse.Builder("ERROR")
            .withErrors(Arrays.asList("insufficient-funds")).withTaxCollected(00L).build();
      }
    } else {
      return new TransferResponse.Builder("ERROR").withErrors(Arrays.asList("limit_exceeded"))
          .withTaxCollected(00L).build();
    }
  }

  @Override
  public AccountDto validateWhetherAccountExists(String account) {
     AccountEntity accountEntity = accountRepository.findByNumAccount(account);
    return new AccountDto.Builder("OK").withErrors(Arrays.asList()).withAccountBalance(accountEntity.getAccountBalance()).build();
  }

  private Long calculateTaxes(Long amount, String currency) {

    Double cadCurrencyValue = retrieveCurrencyValue(currency);

    BigDecimal currentTax;

    if (amount > 100L) {

      currentTax = (BigDecimal.valueOf(amount).multiply(new BigDecimal("0.5"))
          .divide(new BigDecimal("100")));

    } else {
      currentTax = (BigDecimal.valueOf(amount).multiply(new BigDecimal("0.2"))
          .divide(new BigDecimal("100")));

    }
    cadCurrentTax = new BigDecimal(cadCurrencyValue).multiply(currentTax);
    return currentTax.longValue();
  }

  private Double retrieveCurrencyValue(String currency) {
    Map<String, Object> map = apiCurrencyInvoker.getAvailableCurrencies(currency);
    HashMap currenciesMap = null;
    Double cadCurrencyValue = null;

    currenciesMap = (HashMap) map.get("rates");
    cadCurrencyValue = (Double) currenciesMap.get(otherCurrency);

    return cadCurrencyValue;
  }
}
