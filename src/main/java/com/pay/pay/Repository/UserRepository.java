package com.pay.pay.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pay.pay.Models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByCpf(String cpf);
}
