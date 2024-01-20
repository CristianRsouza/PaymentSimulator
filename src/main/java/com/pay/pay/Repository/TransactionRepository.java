package com.pay.pay.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.pay.pay.Models.TransactionModel;

public interface TransactionRepository extends JpaRepository<TransactionModel, Integer> {
}
