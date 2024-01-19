package com.pay.pay.Controllers;

import java.util.List;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import com.pay.pay.Models.UserModel;

@RestController
@RequestMapping("/api/Users")
public class UserControllers {
  
  private List<UserModel> users = new ArrayList<>();

  @GetMapping
  public List<UserModel> getUsers () {
    return users;
  }

  @PostMapping
  public ResponseEntity<Object> register(@RequestBody UserModel newModel) {
    
    String newCpf = newModel.getCpf(); 

      if(cpfAlredyExist(newCpf)) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF already exists");
      }

      users.add(newModel);
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(newModel);

  }

  private boolean cpfAlredyExist(String newCpf) {
    Optional<UserModel> existingUser = users.stream().filter(user -> user.getCpf().equals(newCpf)).findFirst();
    return existingUser.isPresent();
  }
  
}
