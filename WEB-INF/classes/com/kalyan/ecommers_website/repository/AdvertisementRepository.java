package WEB-INF.classes.com.kalyan.ecommers_website.repository;

import com.kalyan.ecommers_website.model.Advertisement;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
  List<Advertisement> findByIsActiveTrueOrderByDisplayOrderAsc();
}


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\repository\AdvertisementRepository.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */