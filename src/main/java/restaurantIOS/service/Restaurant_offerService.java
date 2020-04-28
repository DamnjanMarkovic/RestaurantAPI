package restaurantIOS.service;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import restaurantIOS.models.*;
import restaurantIOS.models.dto.*;
import restaurantIOS.repository.ImagesRepository;
import restaurantIOS.repository.Restaurant_offerRepository;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class Restaurant_offerService {


    private ImagesRepository imagesRepository;

    public Restaurant_offerService(ImagesRepository imagesRepository, Restaurant_offerRepository restaurant_offerRepository) {
        this.imagesRepository = imagesRepository;
        this.restaurant_offerRepository = restaurant_offerRepository;
    }
    private Restaurant_offerRepository restaurant_offerRepository;

    @Transactional
    public List<Restaurant_offer> getAll() {
        return restaurant_offerRepository.findAll();
    }


    @Transactional
    public List<Restaurant_offer> getRestaurant(Integer id) {
        return restaurant_offerRepository.findAllById(Collections.singleton(id));
    }


    @Transactional
    public Restaurant_offer save(RestaurantOfferRequest restaurantOfferRequest) throws SQLException {

        Restaurant_offer restaurant_offer =
                new Restaurant_offer(restaurantOfferRequest.getRestaurant_offer_name(),
                restaurantOfferRequest.getRestaurant_offer_price(), restaurantOfferRequest.getOffer_type(),
                restaurantOfferRequest.getId_image());

        Restaurant_offer restaurantOfferNew = restaurant_offerRepository.save(restaurant_offer);

        for (IngredientsInOfferDTO roiDTO: restaurantOfferRequest.getListIngredientsInOffer()             ) {

            restaurant_offerRepository.connectOfferAndIngredients(restaurantOfferNew.getId(),
                    roiDTO.getId_ingredient(), roiDTO.getQuantity());

        }




        return restaurantOfferNew;

    }





    @Transactional
    public Optional<Restaurant_offer> getRestaurantOfferOnName(String restaurant_offer_name) {

        Restaurant_offer restaurant_offerOLD = new Restaurant_offer();
        restaurant_offerOLD.setRestaurant_offer_name(restaurant_offer_name);
        Example<Restaurant_offer> restaurant_offerExample = Example.of(restaurant_offerOLD);
        return restaurant_offerRepository.findOne(restaurant_offerExample);
    }


    @Transactional
    public List<AvailableOffers> getAvailableOffersInRestaurant(Integer id){
        List<Restaurant_offer> restOffs = restaurant_offerRepository.getAvailableOffersInRestaurant(id);

        List<AvailableOffers> availableOffers = returnAvailableOffersInRestaurant(restOffs, id);


        return availableOffers;


    }

    private List<AvailableOffers> returnAvailableOffersInRestaurant(List<Restaurant_offer> restOffs, int id) {
        List<AvailableOffers> availableOffers = new ArrayList<>();
        AvailableOffers availableOffersOne = null;
        double quantityAvailable = 0;
        double quantity = 0;
        //IngredientsInOffer ingredientsInOffer1 = null;
        //prodji kroz sve ponude pristigle iz baze
        for (Restaurant_offer offs : restOffs) {
            //kreiraj novi set artikala za tu ponudu i prodji kroz artikle svake ponude
            Set<IngredientsInOffer> ingredientsInOffer = new HashSet<>();

            for (Ingredients ingre : offs.getIngredients()) {
                IngredientsInOffer ingredientsInOffer1 = new IngredientsInOffer(ingre.getIngredient_name());
                //za svaki artikal nadji kolicinu koja ide u ponudu (jer se kolicina nalazi u tabeli koja spaja ponude i artikle)
                for (Restaurant_offer_ingredients roi : ingre.getRestaurant_offer_ingredients()) {
                    //preciziraj da se ta kolicina artikla odnosi upravo na tu ponudu
                    if (roi.getId_restaurant_offer() == offs.getId()) {
                        //prodji kroz sve restorane
                        quantity = roi.getQuantity();
                        for (Restaurant rst : ingre.getRestaurants())
                            //preciziraj da se odnosi samo na konkretan restoran za koji je stigao upit
                            if (rst.getId_restaurant() == id) {
                                //prodji kroz sve raspolozive artikle vezane (jer se raspoloziva kolicina nalazi u tabeli koja spaja artikle i restoran)
                                for (Available_ingredients avg : rst.getAvailable_ingredients()) {
                                    //preciziraj da se kolicina odnosi upravo na taj artikal (u prethodno preciziranom restoranu)
                                    if (avg.getId_ingredients() == ingre.getId_ingredient()) {

                                        //kreiraj artikal
                                        quantityAvailable = avg.getQuantityAvailable();
                                         ingredientsInOffer1 =
                                                new IngredientsInOffer(ingre.getIngredient_name(), ingre.getPurchase_price(), ingre.getQuantity_measure(),
                                                        quantity, quantityAvailable);
                                    }
                                }
                            }
                    }
                }

                //ubaci artikal u set
                ingredientsInOffer.add(ingredientsInOffer1);



            }
            availableOffersOne = new AvailableOffers(offs.getId(), offs.getRestaurant_offer_name(),
                    offs.getRestaurant_offer_price(), offs.getOffer_type(), offs.getId_image(), ingredientsInOffer);

            List<IngredientsInOffer> list = new ArrayList<IngredientsInOffer>(availableOffersOne.getIngredientsInOffer());


            if (checkIfExist (list)){
                availableOffers.add(availableOffersOne);
            }
        }
            return availableOffers;

    }

    private boolean checkIfExist(List<IngredientsInOffer> ingredientsInOffers){
        boolean[] bool = new boolean[ingredientsInOffers.size()];
        for (int i = 0; i < ingredientsInOffers.size(); i++) {

            if (ingredientsInOffers.get(i).getQuantityAvailable()>0 && ingredientsInOffers.get(i).getQuantityAvailable()
                    >= ingredientsInOffers.get(i).getQuantity()) {
                    bool[i] = true;

                }
            }

        boolean value1 = checkBooleanValues(bool);
        return value1;

    }

    private boolean checkBooleanValues(boolean[] bool) {
        boolean value1 = true;
        for (int i = 0; i < bool.length; i++) {
            if (bool[i] == false) {
                value1 = false;
                break;
            }
        }
        return value1;
    }

    public List<AvailableOffers> getAllAvailableOffers() {

        List<Restaurant_offer> restOffs = restaurant_offerRepository.getAllAvailableOffers();
            List<AvailableOffers> availableOffers = new ArrayList<>();
            AvailableOffers availableOffersOne = null;
            double quantity = 10;
            for (Restaurant_offer offs : restOffs) {
                Set<IngredientsInOffer> ingredientsInOffer = new HashSet<>();
                for (Ingredients ingre : offs.getIngredients()) {
                    IngredientsInOffer ingredientsInOffer1 = new IngredientsInOffer(ingre.getIngredient_name());
                    for (Restaurant_offer_ingredients roi : ingre.getRestaurant_offer_ingredients()) {
                        if (roi.getId_restaurant_offer() == offs.getId()) {
                           quantity = roi.getQuantity();
                            for (Restaurant rst : ingre.getRestaurants())
                                      for (Available_ingredients avg : rst.getAvailable_ingredients()) {
                                               if (avg.getId_ingredients() == ingre.getId_ingredient()) {
                        ingredientsInOffer1 = new IngredientsInOffer(ingre.getIngredient_name(), ingre.getPurchase_price(), ingre.getQuantity_measure(),
                                                            quantity, 0);
                                        }
                                }
                        }
                    }
                    ingredientsInOffer.add(ingredientsInOffer1);
                }
                availableOffersOne = new AvailableOffers(offs.getId(), offs.getRestaurant_offer_name(),
                        offs.getRestaurant_offer_price(), offs.getOffer_type(), offs.getId_image(), ingredientsInOffer);

                List<IngredientsInOffer> list = new ArrayList<IngredientsInOffer>(availableOffersOne.getIngredientsInOffer());

                    availableOffers.add(availableOffersOne);
            }
            return availableOffers;

    }
    @Transactional
    public List<Images> getOffersPhotos() {
        List<Integer> listOffersIDs = restaurant_offerRepository.getOffersIDs();
        return getAllOffersPhotos(listOffersIDs);

    }
    @Transactional
    private List<Images> getAllOffersPhotos(List<Integer> listOffersIDs) {
        Images imageOffer = new Images();
        List<Images> listImageOffers = new ArrayList<>();
        for (Integer inter: listOffersIDs             ) {
            imageOffer = imagesRepository.getSpecificPhotos(inter);
            if (imageOffer!=null) {
                if (!listImageOffers.contains(imageOffer)) {
                    listImageOffers.add(imageOffer);
                }
            }
        }
        return listImageOffers;
    }



}



/*
// OVAKO VRACA SVE STO IMA U RESTORANU BEZ OBZIRA DA LI JE TO DOVOLJNO ILI NE

 public List<AvailableOffers> getAvailableOffersInRestaurant(@PathVariable Integer id){
        List<AvailableOffers> availableOffers = new ArrayList<>();
        AvailableOffers availableOffersOne = null;
        List<Restaurant_offer> restOffs = restaurant_offerRepository.getAvailableOffersInRestaurant(id);
        //prodji kroz sve ponude pristigle iz baze
        for (Restaurant_offer offs : restOffs) {
            //kreiraj novi set artikala za tu ponudu i prodji kroz artikle svake ponude
            Set<IngredientsInOffer>ingredientsInOffer = new HashSet<>();
            for (Ingredients ingre : offs.getIngredients()) {
                //za svaki artikal nadji kolicinu koja ide u ponudu (jer se kolicina nalazi u tabeli koja spaja ponude i artikle)
                for (Restaurant_offer_ingredients roi: ingre.getRestaurant_offer_ingredients()                         ) {
                    //preciziraj da se ta kolicina artikla odnosi upravo na tu ponudu
                    if (roi.getId_restaurant_offer() == offs.getId()) {
                        //prodji kroz sve restorane
                        for (Restaurant rst: ingre.getRestaurants())
                            //preciziraj da se odnosi samo na konkretan restoran za koji je stigao upit
                                if (rst.getId_restaurant() == id){
                                //prodji kroz sve raspolozive artikle vezane (jer se raspoloziva kolicina nalazi u tabeli koja spaja artikle i restoran)
                                for (Available_ingredients avg: rst.getAvailable_ingredients()) {
                                    //preciziraj da se kolicina odnosi upravo na taj artikal (u prethodno preciziranom restoranu)
                                    if (avg.getId_ingredients() == ingre.getId_ingredient()) {

                                            //kreiraj artikal
                                            IngredientsInOffer ingredientsInOffer1 =
                                                    new IngredientsInOffer(ingre.getIngredient_name(), ingre.getPurchase_price(), ingre.getQuantity_measure(),
                                                            roi.getQuantity(), avg.getQuantityAvailable());
                                            //ubaci artikal u set
                                            ingredientsInOffer.add(ingredientsInOffer1);

                                    }
                                }
                        }
                    }
                }
            }
            availableOffersOne = new AvailableOffers(offs.getId(), offs.getRestaurant_offer_name(),
                    offs.getRestaurant_offer_price(), offs.getOffer_type(), offs.getImage(), ingredientsInOffer);
            availableOffers.add(availableOffersOne);
        }

        return availableOffers;
                }
 */