package com.pay.pay.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.pay.pay.Models.TransactionModel;
import com.pay.pay.Repository.TransactionRepository;
import com.pay.pay.Repository.UserRepository;
import com.pay.pay.Models.UserModel;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/Transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public List<TransactionModel> getTransactions() {
        return transactionRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody TransactionModel newTransaction) {
        if (isInviterExists(newTransaction.getUserInviter())) {
            System.out.println("O inviter existe");
            if (isReceiverExists(newTransaction.getUserReceiver())) {
                System.out.println("O destinatario existe");
                if (haveEnoughMoney(newTransaction.getValue_in_cents(), newTransaction.getUserInviter())) {
                    
                    UserModel userInviter = userRepository.findByCpf(newTransaction.getUserInviter()).orElseThrow();
                    UserModel userReceiver = userRepository.findByCpf(newTransaction.getUserReceiver()).orElseThrow();
        
                    userInviter.setWallet(userInviter.getWallet() - newTransaction.getValue_in_cents());
                    userRepository.save(userInviter);
                    userRepository.save(userReceiver);
        
                    System.out.println("Transação realizada com sucesso");
                    newTransaction.setId(UUID.randomUUID());
                    transactionRepository.save(newTransaction);
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(newTransaction);
                }
            }
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid transaction");
    }
    
    


    private boolean isInviterExists(String userInviter) {
        return userRepository.existsByCpf(userInviter);
    }

    private boolean isReceiverExists(String userReceiver) {
        return userRepository.existsByCpf(userReceiver);
    }

    private boolean haveEnoughMoney(Long value, String userInviter) {
        Optional<UserModel> thisUserOptional = userRepository.findByCpf(userInviter);
        return thisUserOptional.map(thisUser -> thisUser.getWallet() >= value).orElse(false);
    }
}
