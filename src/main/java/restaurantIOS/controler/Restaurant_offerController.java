package restaurantIOS.controler;


import org.apache.commons.compress.utils.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import restaurantIOS.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restaurantIOS.models.dto.AvailableOffers;
import restaurantIOS.models.dto.UserResponse;
import restaurantIOS.service.Restaurant_offerService;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping(value = "/rest/offers")
public class Restaurant_offerController {


    private Restaurant_offerService restaurant_offerService;

    public Restaurant_offerController(Restaurant_offerService restaurant_offerService) {
        this.restaurant_offerService = restaurant_offerService;
    }
    @RequestMapping(value = "/getOffersImages", produces="application/zip")
    public ResponseEntity<StreamingResponseBody> getOffersImages() {
        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=\"offerImages.zip\"")
                .body(out -> {
                    ZipOutputStream zipOutputStream = new ZipOutputStream(out);
                    ArrayList<File> files = new ArrayList<>();
                    List<Images> photos = restaurant_offerService.getOffersPhotos();
                    for (Images imags: photos						 ) {
                        if (imags!=null) {
                            files.add(new File(imags.getImageLocation()));
                        }
                    }
                    for (File file : files) {
                        zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                        FileInputStream fileInputStream = new FileInputStream(file);
                        IOUtils.copy(fileInputStream, zipOutputStream);
                        fileInputStream.close();
                        zipOutputStream.closeEntry();
                    }
                    zipOutputStream.close();
                });
    }
    @GetMapping(value = "/all")
    public List<Restaurant_offer> getAll(){
        return restaurant_offerService.getAll();
    }


    @GetMapping("/{id}")
    public List<Restaurant_offer> getRestaurant(@PathVariable Integer id){
        return restaurant_offerService.getRestaurant(id);
    }

    @GetMapping("/offer_name/{restaurant_offer_name}")
    public Optional<Restaurant_offer> getRestaurantOfferOnName(@PathVariable String restaurant_offer_name) {
        return restaurant_offerService.getRestaurantOfferOnName(restaurant_offer_name);

    }


    @PostMapping(value = "/load")
    public List<Restaurant_offer>persist(@RequestBody final Restaurant_offer restaurant_offer){

        restaurant_offerService.save(restaurant_offer);
        return restaurant_offerService.getAll();
    }

    @GetMapping("/availableOffers/{id}")
    public List<AvailableOffers> getAvailableOffersInRestaurant(@PathVariable Integer id){

        return restaurant_offerService.getAvailableOffersInRestaurant(id);

    }

    @GetMapping("/allAvailableOffers")
    public List<AvailableOffers> getAllAvailableOffers(){

        return restaurant_offerService.getAllAvailableOffers();

    }

}

