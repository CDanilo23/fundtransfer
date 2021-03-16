package com.fundtransfer.model.dao;

import com.fundtransfer.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

  @Query("select account from AccountEntity account where account.numAccount = :numAccount")
  AccountEntity findByNumAccount(String numAccount);


}
