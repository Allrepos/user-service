package com.epam.userservice.repository;

import com.epam.userservice.entity.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDao, Integer> {

    Optional<UserDao> findByEmailId(String emailId);

}
