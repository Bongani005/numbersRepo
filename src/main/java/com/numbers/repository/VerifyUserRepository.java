package com.numbers.repository;

import com.numbers.entities.VerifyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifyUserRepository extends JpaRepository<VerifyUser,String> {
}
