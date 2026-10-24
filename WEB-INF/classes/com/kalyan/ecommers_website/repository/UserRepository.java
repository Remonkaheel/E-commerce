package WEB-INF.classes.com.kalyan.ecommers_website.repository;

import com.kalyan.ecommers_website.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String paramString);
  
  Optional<User> findByPhone(String paramString);
  
  Optional<User> findByUsername(String paramString);
}


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\repository\UserRepository.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */