package com.pay.pay.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pay.pay.Models.TransactionModel;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionModel, UUID> {
}
