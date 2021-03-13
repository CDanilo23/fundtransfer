package com.fundtransfer.model.dao;

import com.fundtransfer.model.entity.AttemptEntity;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptRepository extends JpaRepository<AttemptEntity, Long> {

    @Query("select attempt from AttemptEntity attempt where attempt.originAccount = :originAccount and attempt.attempts >= 3")
    AttemptEntity findAttemptByOriginAccountAndCurrentDate(String originAccount);

}
