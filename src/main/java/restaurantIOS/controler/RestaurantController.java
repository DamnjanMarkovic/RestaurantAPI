package restaurantIOS.controler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import restaurantIOS.models.Images;
import restaurantIOS.models.Restaurant;
import org.springframework.web.bind.annotation.*;
import restaurantIOS.models.dto.UserRequest;
import restaurantIOS.service.ImagesService;
import restaurantIOS.service.RestaurantService;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rest/restaurants")
public class RestaurantController {


    private final RestaurantService restaurantService;
    private final ImagesService imagesService;

    public RestaurantController(RestaurantService restaurantService, ImagesService imagesService) {
        this.restaurantService = restaurantService;
        this.imagesService = imagesService;
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

        }

    }

    @DeleteMapping("/deleteRestaurant/{id_restaurant}")
    public void deleteRestaurant (@PathVariable Integer id_restaurant) throws Exception {

        restaurantService.deleteRestaurant(id_restaurant);

    }


    @PostMapping(value = "/loadRestaurant", consumes = {"multipart/form-data"})
    public String saveUser (@RequestParam("imageFile") @PathVariable MultipartFile imageFile,
                            Restaurant restaurant){
        String result = null;
        String response = null;
        Images images = new Images();
        images.setImagename(imageFile.getOriginalFilename());
        try {
            Integer id_image = imagesService.saveSpecificImage(imageFile, images);
            restaurant.setId_image(id_image);
            response = restaurantService.save(restaurant);
            result = response;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping(value = "/load")
    public List<Restaurant> persist(@RequestBody final Restaurant restaurant) throws SQLException {

        restaurantService.save(restaurant);
        return restaurantService.getAll();
    }


}
