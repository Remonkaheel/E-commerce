/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.service;
/*    */ 
/*    */ import com.kalyan.ecommers_website.model.DTO.RegistrationDto;
/*    */ import com.kalyan.ecommers_website.model.Role;
/*    */ import com.kalyan.ecommers_website.model.User;
/*    */ import com.kalyan.ecommers_website.repository.UserRepository;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.security.crypto.password.PasswordEncoder;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class UserService
/*    */ {
/*    */   @Autowired
/*    */   private UserRepository userRepository;
/*    */   @Autowired
/*    */   private PasswordEncoder passwordEncoder;
/*    */   
/*    */   public String saveNewUser(RegistrationDto registrationDto) {
/* 22 */     if (this.userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
/* 23 */       return "Email is already registered.";
/*    */     }
/*    */ 
/*    */     
/* 27 */     if (this.userRepository.findByPhone(registrationDto.getPhone()).isPresent()) {
/* 28 */       return "Phone number is already registered.";
/*    */     }
/*    */ 
/*    */     
/* 32 */     User user = new User();
/* 33 */     user.setUsername(registrationDto.getName());
/* 34 */     user.setEmail(registrationDto.getEmail());
/* 35 */     user.setPassword(this.passwordEncoder.encode(registrationDto.getPassword()));
/* 36 */     user.setPhone(registrationDto.getPhone());
/* 37 */     user.setRole(Role.ROLE_CUSTOMER);
/* 38 */     this.userRepository.save(user);
/*    */     
/* 40 */     return "success";
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\service\UserService.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */