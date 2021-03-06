package restaurantIOS.controler;


import org.apache.commons.compress.utils.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import restaurantIOS.models.*;
import org.springframework.web.bind.annotation.*;
import restaurantIOS.models.dto.AvailableOffers;
import restaurantIOS.models.dto.IngredientsInOfferDTO;
import restaurantIOS.models.dto.MessageOfferDTO;
import restaurantIOS.models.dto.RestaurantOfferRequest;
import restaurantIOS.service.ImagesService;
import restaurantIOS.service.Restaurant_offerService;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping(value = "/rest/offers")
public class Restaurant_offerController {


    private final Restaurant_offerService restaurant_offerService;
    private final ImagesService imagesService;

    public Restaurant_offerController(Restaurant_offerService restaurant_offerService, ImagesService imagesService) {
        this.restaurant_offerService = restaurant_offerService;
        this.imagesService = imagesService;
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
    @PostMapping(value = "/saveOrUpdate", consumes = {"multipart/form-data"})
    public Restaurant_offer saveOrUpdateNewOffer (@RequestParam("imageFile") @PathVariable MultipartFile imageFile,
                                          RestaurantOfferRequest restaurantOfferRequest,
                                          MessageOfferDTO messageOfferDTO){

        Images images = new Images();
        images.setImagename(imageFile.getOriginalFilename());
        Restaurant_offer restaurant_offer = null;
        try {
            Integer id_image = imagesService.saveSpecificImage(imageFile, images);
            restaurantOfferRequest.setId_image(id_image);
            restaurant_offer = restaurant_offerService.saveOrUpdate(restaurantOfferRequest, messageOfferDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurant_offer;
    }

    @PutMapping(value = "/updateOffer", consumes = {"multipart/form-data"})
    public Restaurant_offer updateExistingOffer (@RequestParam("imageFile") @PathVariable MultipartFile imageFile,
                                                 RestaurantOfferRequest restaurantOfferRequest,
                                                 MessageOfferDTO messageOfferDTO){
        Images images = new Images();
        images.setImagename(imageFile.getOriginalFilename());
        Restaurant_offer restaurant_offer = null;
        try {
            Integer id_image = imagesService.saveSpecificImage(imageFile, images);
            restaurantOfferRequest.setId_image(id_image);
            restaurant_offer = restaurant_offerService.update(restaurantOfferRequest, messageOfferDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurant_offer;
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

