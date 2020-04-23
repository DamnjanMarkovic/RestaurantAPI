package restaurantIOS.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restaurantIOS.models.Restaurant;
import restaurantIOS.repository.RestaurantRepository;

import javax.persistence.EntityNotFoundException;
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

//        try {
            return restaurantRepository.findById((id));
//        }catch (EntityNotFoundException e){
//            throw new EntityNotFoundException();
//        }


    }



    @Transactional
    public List<Restaurant> save(Restaurant restaurant){
        restaurantRepository.save(restaurant);
        return restaurantRepository.findAll();
    }


}
