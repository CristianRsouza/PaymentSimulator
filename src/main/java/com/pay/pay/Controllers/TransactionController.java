package com.pay.pay.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.pay.pay.Models.TransactionModel;
import com.pay.pay.Models.UserModel;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private List<TransactionModel> Transactions = new ArrayList<>();
    private List<UserModel> users = new ArrayList<>();

    @GetMapping
    public List<TransactionModel> getTransactions () {
      return Transactions;
    }

    @PostMapping
      public ResponseEntity<Object> register(@RequestBody TransactionModel newTransaction) {



        if(InviterExist(newTransaction.getUserInviter())) {
            if(ReciverExits(newTransaction.getUserInviter())) {
                if(HaveMoneyEnougth(newTransaction.getUserInviter())) {

                }
            }

        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
      }

      private boolean HaveMoneyEnougth(UserModel userInviter) {
        

        return false;
      
      }

      private boolean ReciverExits(UserModel userReciver) {
        return users.contains(userReciver);

      }

      private boolean InviterExist(UserModel userInviter) {
          return users.contains(userInviter);
      }

}
