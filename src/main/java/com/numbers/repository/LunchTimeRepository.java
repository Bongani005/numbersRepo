package com.numbers.repository;

import com.numbers.entities.LunchTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface LunchTimeRepository extends JpaRepository<LunchTime, LocalDate> {
    @Modifying
    @Query(value = "DELETE FROM LunchTime l WHERE l.resultDate < :#{#localDate}")
    @Transactional
    void deleteByDateLessThan(LocalDate localDate);
}
