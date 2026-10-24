/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.service;
/*    */ 
/*    */ import com.kalyan.ecommers_website.model.User;
/*    */ import com.kalyan.ecommers_website.repository.UserRepository;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.security.core.GrantedAuthority;
/*    */ import org.springframework.security.core.authority.SimpleGrantedAuthority;
/*    */ import org.springframework.security.core.userdetails.User;
/*    */ import org.springframework.security.core.userdetails.UserDetails;
/*    */ import org.springframework.security.core.userdetails.UserDetailsService;
/*    */ import org.springframework.security.core.userdetails.UsernameNotFoundException;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class CustomUserDetailsService
/*    */   implements UserDetailsService
/*    */ {
/*    */   @Autowired
/*    */   private UserRepository userRepository;
/*    */   
/*    */   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
/* 26 */     User user = (User)this.userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
/*    */ 
/*    */     
/* 29 */     Set<GrantedAuthority> authorities = new HashSet<>();
/* 30 */     authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
/*    */ 
/*    */     
/* 33 */     return (UserDetails)new User(user
/* 34 */         .getEmail(), user
/* 35 */         .getPassword(), authorities);
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\service\CustomUserDetailsService.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */