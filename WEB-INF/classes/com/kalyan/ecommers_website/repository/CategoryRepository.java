package WEB-INF.classes.com.kalyan.ecommers_website.repository;

import com.kalyan.ecommers_website.model.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  Optional<Category> findById(Long paramLong);
  
  Optional<Category> findByName(String paramString);
}


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\repository\CategoryRepository.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */