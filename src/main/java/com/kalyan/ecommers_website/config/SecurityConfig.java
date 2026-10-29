package com.kalyan.ecommers_website.config;
import com.kalyan.ecommers_website.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return web -> web.ignoring().requestMatchers(new String[] { "/images/**", "/css/**", "/js/**", "/webjars/**", "/favicon.ico", "/logo_11.jpg" });
  }


  
  @Autowired
  private CustomUserDetailsService customUserDetailsService;


  
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      
      .csrf(csrf -> csrf.ignoringRequestMatchers(new RequestMatcher[] {



            
            (RequestMatcher)new AntPathRequestMatcher("/cart/**"), (RequestMatcher)new AntPathRequestMatcher("/api/**"), (RequestMatcher)new AntPathRequestMatcher("/cart/**"), (RequestMatcher)new AntPathRequestMatcher("/api/**"), (RequestMatcher)new AntPathRequestMatcher("/admin/categories/**")




          
          })).authorizeHttpRequests(auth -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)auth.requestMatchers(new String[] {

            
            "/", "/css/**", "/js/**", "/images/**", "/login", "/register", "/category/**", "/contact", "/cart/**", "/orders/**",

            
            "/api/**", "/about", "/privacy", "/product/**", "/shipping", "/return"
          })).permitAll().requestMatchers(new String[] {
            
            "/admin/**"
          
          })).hasRole("ADMIN").anyRequest()).authenticated()).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))



      
      .formLogin(form -> ((FormLoginConfigurer)((FormLoginConfigurer)form.loginPage("/login").defaultSuccessUrl("/home", true)).failureUrl("/login?error=true")).permitAll())





      
      .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll());


















    
    return (SecurityFilterChain)http.build();
  }
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    return (PasswordEncoder)new BCryptPasswordEncoder();
  }

  @Configuration
  public static class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/about").setViewName("about");
      registry.addViewController("/privacy").setViewName("privacy");
      registry.addViewController("/return").setViewName("return");
      registry.addViewController("/shipping").setViewName("shipping");
    }
  }
}


