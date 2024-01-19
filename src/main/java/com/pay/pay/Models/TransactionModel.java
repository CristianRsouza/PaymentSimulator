package com.pay.pay.Models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Transactions")
@Data
public class TransactionModel {
      @Id
    private UUID id;
    private UserModel userInviter;
    private UserModel userReciver;
    private Integer value;


}