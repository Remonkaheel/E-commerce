package com.kalyan.ecommers_website.service;

import com.kalyan.ecommers_website.model.DTO.RegistrationDto;
import com.kalyan.ecommers_website.model.Role;
import com.kalyan.ecommers_website.model.User;
import com.kalyan.ecommers_website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService
{
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  
  public String saveNewUser(RegistrationDto registrationDto) {
    if (this.userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
      return "Email is already registered.";
    }

    
    if (this.userRepository.findByPhone(registrationDto.getPhone()).isPresent()) {
      return "Phone number is already registered.";
    }

    
    User user = new User();
    user.setUsername(registrationDto.getName());
    user.setEmail(registrationDto.getEmail());
    user.setPassword(this.passwordEncoder.encode(registrationDto.getPassword()));
    user.setPhone(registrationDto.getPhone());
    user.setRole(Role.ROLE_CUSTOMER);
    this.userRepository.save(user);
    
    return "success";
  }
}


