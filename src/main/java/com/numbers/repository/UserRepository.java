package com.numbers.repository;

import com.numbers.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {

//    String query = "UPDATE u USER SET u.status = DEACTIVATED WHERE u.username= :#{id}";
//
//    String queryActivate = "UPDATE u USER SET u.status = ACTIVE WHERE u.username= :#{id}";
//
//    @Modifying(flushAutomatically = true)
//    @Query(value = query)
//    @Transactional
//    void deleteUserChangeStatus(@Param("id") String id);
//
//    @Modifying(flushAutomatically = true)
//    @Query(value = queryActivate)
//    @Transactional
//    User activateUser(@Param("username") String username);
}
