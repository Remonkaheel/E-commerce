/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.config;
/*    */ import com.kalyan.ecommers_website.service.CustomUserDetailsService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.annotation.Bean;
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ import org.springframework.security.config.annotation.web.builders.HttpSecurity;
/*    */ import org.springframework.security.config.annotation.web.builders.WebSecurity;
/*    */ import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
/*    */ import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
/*    */ import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
/*    */ import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
/*    */ import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
/*    */ import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
/*    */ import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
/*    */ import org.springframework.security.config.http.SessionCreationPolicy;
/*    */ import org.springframework.security.crypto.password.PasswordEncoder;
/*    */ import org.springframework.security.web.SecurityFilterChain;
/*    */ import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
/*    */ import org.springframework.security.web.util.matcher.RequestMatcher;
/*    */ 
/*    */ @Configuration
/*    */ @EnableWebSecurity
/*    */ public class SecurityConfig {
/*    */   @Bean
/*    */   public WebSecurityCustomizer webSecurityCustomizer() {
/* 26 */     return web -> web.ignoring().requestMatchers(new String[] { "/images/**", "/css/**", "/js/**", "/webjars/**", "/favicon.ico", "/logo_11.jpg" });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Autowired
/*    */   private CustomUserDetailsService customUserDetailsService;
/*    */ 
/*    */ 
/*    */   
/*    */   @Bean
/*    */   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
/* 38 */     http
/*    */       
/* 40 */       .csrf(csrf -> csrf.ignoringRequestMatchers(new RequestMatcher[] {
/*    */ 
/*    */ 
/*    */ 
/*    */             
/*    */             (RequestMatcher)new AntPathRequestMatcher("/cart/**"), (RequestMatcher)new AntPathRequestMatcher("/api/**"), (RequestMatcher)new AntPathRequestMatcher("/cart/**"), (RequestMatcher)new AntPathRequestMatcher("/api/**"), (RequestMatcher)new AntPathRequestMatcher("/admin/categories/**")
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 51 */           })).authorizeHttpRequests(auth -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)auth.requestMatchers(new String[] {
/*    */ 
/*    */             
/*    */             "/", "/css/**", "/js/**", "/images/**", "/login", "/register", "/category/**", "/contact", "/cart/**", "/orders/**",
/*    */ 
/*    */             
/*    */             "/api/**", "/about", "/privacy", "/product/**", "/shipping", "/return"
/*    */           })).permitAll().requestMatchers(new String[] {
/*    */             
/*    */             "/admin/**"
/*    */           
/* 62 */           })).hasRole("ADMIN").anyRequest()).authenticated()).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 67 */       .formLogin(form -> ((FormLoginConfigurer)((FormLoginConfigurer)form.loginPage("/login").defaultSuccessUrl("/home", true)).failureUrl("/login?error=true")).permitAll())
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 74 */       .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 94 */     return (SecurityFilterChain)http.build();
/*    */   }
/*    */   
/*    */   @Bean
/*    */   public PasswordEncoder passwordEncoder() {
/* 99 */     return (PasswordEncoder)new BCryptPasswordEncoder();
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\config\SecurityConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */