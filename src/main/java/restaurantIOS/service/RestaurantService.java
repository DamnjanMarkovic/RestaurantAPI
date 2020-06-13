package restaurantIOS.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restaurantIOS.models.Restaurant;
import restaurantIOS.repository.RestaurantRepository;

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
    public void deleteRestaurant(Integer id)  {

        restaurantRepository.deleteAvailableIngredientsInRestaurant(id);

        restaurantRepository.deleteById(id);

    }

    @Transactional
    public String save(Restaurant restaurant) throws SQLException {
        String result = null;
        Restaurant restaurant1 = restaurantRepository.save(restaurant);

        for (int i = 1; i <10 ; i++) {
            restaurantRepository.insertDinningTables(i, restaurant1.getId_restaurant(), 6);
        }

        result = "Restaurant inserted in the DB";
        return result;
    }


}
