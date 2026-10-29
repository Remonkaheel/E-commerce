package com.kalyan.ecommers_website.service;

import com.kalyan.ecommers_website.model.User;
import com.kalyan.ecommers_website.repository.UserRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class CustomUserDetailsService
  implements UserDetailsService
{
  @Autowired
  private UserRepository userRepository;
  
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = (User)this.userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

    
    Set<GrantedAuthority> authorities = new HashSet<>();
    authorities.add(new SimpleGrantedAuthority(user.getRole().name()));

    
    return (UserDetails)new org.springframework.security.core.userdetails.User(user
        .getEmail(), user
        .getPassword(), authorities);
  }
}


