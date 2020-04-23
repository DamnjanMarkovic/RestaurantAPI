package restaurantIOS.controler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import restaurantIOS.models.Restaurant;
import org.springframework.web.bind.annotation.*;
import restaurantIOS.service.RestaurantService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rest/restaurants")
public class RestaurantController {


    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @GetMapping(value = "/all")
    public ResponseEntity<List<Restaurant>> getAll(){
        List<Restaurant> restaurants;
            restaurants = restaurantService.getAll();

        return new ResponseEntity<>(restaurants, HttpStatus.OK);

    }



    @GetMapping("/{id}")
    public Optional<Restaurant> getRestaurant(@PathVariable Integer id) throws Exception {
        try {
            return restaurantService.getRestaurant(id);
        } catch (Exception e){
            throw new Exception("poruka", e.initCause(e.getCause()));
//        e.getCause();
        }//return restaurantService.getRestaurant(1);

    }


    @PostMapping(value = "/load")
    public List<Restaurant> persist(@RequestBody final Restaurant restaurant){

        restaurantService.save(restaurant);
        return restaurantService.getAll();
    }


}
