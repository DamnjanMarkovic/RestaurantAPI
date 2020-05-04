package restaurantIOS.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restaurantIOS.models.Restaurant;
import restaurantIOS.models.User;
import restaurantIOS.models.dto.UserRequest;
import restaurantIOS.repository.RestaurantRepository;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {


    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    public List<Restaurant> getAll(){
        return restaurantRepository.findAll();
    }

    @Transactional
    public Optional<Restaurant> getRestaurant(Integer id)  {

            return restaurantRepository.findById((id));

    }

    @Transactional
    public String save(Restaurant restaurant) throws SQLException {
        String result = null;
        restaurantRepository.save(restaurant);
        result = "Restaurant inserted in the DB";
        return result;
    }


}
