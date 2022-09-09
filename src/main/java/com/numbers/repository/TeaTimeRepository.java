package com.numbers.repository;

import com.numbers.entities.TeaTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface TeaTimeRepository extends JpaRepository<TeaTime, LocalDate> {

    @Modifying
    @Query(value = "DELETE FROM TeaTime t WHERE t.resultDate < :#{#localDate}")
    @Transactional
    void deleteByDateLessThan(LocalDate localDate);
}
