package restaurantIOS.repository;


import org.springframework.stereotype.Repository;
import restaurantIOS.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
