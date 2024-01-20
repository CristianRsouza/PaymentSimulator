package com.pay.pay.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.pay.pay.Models.TransactionModel;
import com.pay.pay.Repository.TransactionRepository;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/Transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;
 
    @GetMapping
    public List<TransactionModel> getTransactions() {
        return transactionRepository.findAll();
    }
    
}
