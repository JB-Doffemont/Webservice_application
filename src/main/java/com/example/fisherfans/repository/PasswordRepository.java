package com.example.fisherfans.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.fisherfans.entity.Password;

@Repository
public interface PasswordRepository extends CrudRepository<Password, Long> {
    Password findByPassword(String password);

    void deleteByPassword(String password);
}