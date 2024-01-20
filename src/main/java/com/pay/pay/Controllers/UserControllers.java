package com.pay.pay.Controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pay.pay.Models.UserModel;
import com.pay.pay.Repository.UserRepository;
@RestController
@RequestMapping("/api/Users")
public class UserControllers {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserModel> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody UserModel newModel) {
        String newCpf = newModel.getCpf();

        if (cpfAlreadyExists(newCpf)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF already exists");
        }

        newModel.setId(null); 
        userRepository.save(newModel);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newModel);
    }

    private boolean cpfAlreadyExists(String newCpf) {
        return userRepository.findByCpf(newCpf).isPresent();
    }
}

