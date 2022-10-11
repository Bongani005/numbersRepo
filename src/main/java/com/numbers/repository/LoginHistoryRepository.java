package com.numbers.repository;

import com.numbers.entities.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory,String> {
}
