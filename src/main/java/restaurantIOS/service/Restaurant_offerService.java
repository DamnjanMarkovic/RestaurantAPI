package restaurantIOS.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import restaurantIOS.models.*;
import restaurantIOS.models.dto.*;
import restaurantIOS.repository.ImagesRepository;
import restaurantIOS.repository.IngredientRepository;
import restaurantIOS.repository.Restaurant_offerRepository;

import java.sql.SQLException;
import java.util.*;



@Service
public class Restaurant_offerService {


    private final ImagesRepository imagesRepository;
    private final Restaurant_offerRepository restaurant_offerRepository;
    private final IngredientRepository ingredientRepository;

    public Restaurant_offerService(ImagesRepository imagesRepository, Restaurant_offerRepository restaurant_offerRepository, IngredientRepository ingredientRepository) {
        this.imagesRepository = imagesRepository;
        this.restaurant_offerRepository = restaurant_offerRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Transactional
    public List<Restaurant_offer> getAll() {
        return restaurant_offerRepository.findAll();
    }


    @Transactional
    public List<Restaurant_offer> getRestaurant(Integer id) {
        return restaurant_offerRepository.findAllById(Collections.singleton(id));
    }


    @Modifying
    @Transactional
    public Restaurant_offer saveOrUpdate(RestaurantOfferRequest restaurantOfferRequest, MessageOfferDTO messageOfferDTO) throws SQLException {
        Restaurant_offer restaurant_offer;

        List<IngredientsInOfferDTO> listIngredientsInOffer = getIngredientList(messageOfferDTO.getSpecialMessage());

        if (restaurant_offerRepository.checkOfferexistance(restaurantOfferRequest.getRestaurant_offer_name()) > 0) {
            Integer offerArrivedID = restaurant_offerRepository.findByName(restaurantOfferRequest.getRestaurant_offer_name());
            restaurant_offer =
                    new Restaurant_offer(offerArrivedID, restaurantOfferRequest.getRestaurant_offer_name(),
                            restaurantOfferRequest.getRestaurant_offer_price(), restaurantOfferRequest.getOffer_type(),
                            restaurantOfferRequest.getId_image());
            restaurant_offerRepository.deletePreviousData(offerArrivedID);
            restaurant_offerRepository.updateOffer(restaurantOfferRequest.getRestaurant_offer_name(),
                    restaurantOfferRequest.getRestaurant_offer_price(), restaurantOfferRequest.getOffer_type(),
                    restaurantOfferRequest.getId_image(), offerArrivedID);

        } else {
            Restaurant_offer restaurant_offerArrived =
                    new Restaurant_offer(restaurantOfferRequest.getRestaurant_offer_name(),
                            restaurantOfferRequest.getRestaurant_offer_price(), restaurantOfferRequest.getOffer_type(),
                            restaurantOfferRequest.getId_image());

            restaurant_offer = restaurant_offerRepository.save(restaurant_offerArrived);
        }
            for (IngredientsInOfferDTO roiDTO: listIngredientsInOffer             ) {
                restaurant_offerRepository.connectOfferAndIngredients(restaurant_offer.getId(),
                roiDTO.getId_ingredient(), roiDTO.getQuantity());
            }


        return restaurant_offer;
    }
    @Modifying
    @Transactional
    public Restaurant_offer save(RestaurantOfferRequest restaurantOfferRequest, MessageOfferDTO messageOfferDTO) throws SQLException {

        List<IngredientsInOfferDTO> listIngredientsInOffer = getIngredientList(messageOfferDTO.getSpecialMessage());

        Restaurant_offer restaurant_offer =
                new Restaurant_offer(restaurantOfferRequest.getRestaurant_offer_name(),
                        restaurantOfferRequest.getRestaurant_offer_price(), restaurantOfferRequest.getOffer_type(),
                        restaurantOfferRequest.getId_image());

        Restaurant_offer restaurantOfferNew = restaurant_offerRepository.save(restaurant_offer);

        for (IngredientsInOfferDTO roiDTO: listIngredientsInOffer             ) {
            restaurant_offerRepository.connectOfferAndIngredients(restaurantOfferNew.getId(),
                    roiDTO.getId_ingredient(), roiDTO.getQuantity());
        }
        return restaurantOfferNew;
    }

    @Modifying
    @Transactional
    public Restaurant_offer update(RestaurantOfferRequest restaurantOfferRequest, MessageOfferDTO messageOfferDTO) {
        List<IngredientsInOfferDTO> listIngredientsInOffer = getIngredientList(messageOfferDTO.getSpecialMessage());

        Restaurant_offer restaurant_offer =
                new Restaurant_offer(restaurantOfferRequest.getId_restaurant_offer(), restaurantOfferRequest.getRestaurant_offer_name(),
                        restaurantOfferRequest.getRestaurant_offer_price(), restaurantOfferRequest.getOffer_type(),
                        restaurantOfferRequest.getId_image());

        restaurant_offerRepository.deletePreviousData(restaurantOfferRequest.getId_restaurant_offer());
        restaurant_offerRepository.updateOffer(restaurantOfferRequest.getRestaurant_offer_name(),
                restaurantOfferRequest.getRestaurant_offer_price(), restaurantOfferRequest.getOffer_type(),
                restaurantOfferRequest.getId_image(), restaurantOfferRequest.getId_restaurant_offer());

        for (IngredientsInOfferDTO roiDTO: listIngredientsInOffer             ) {

            restaurant_offerRepository.connectOfferAndIngredients(restaurant_offer.getId(),
                    roiDTO.getId_ingredient(), roiDTO.getQuantity());
        }
        return restaurant_offer;


    }




    public List<IngredientsInOfferDTO> getIngredientList(String message) {
        IngredientsInOfferDTO ingr;
        List<IngredientsInOfferDTO> listIngredientsInOffer = new ArrayList<>();
        String finalMessage = message.substring(0, message.length()-1);

        String[] tokens = finalMessage.split(";");
        for (int i = 0; i <tokens.length ; i = i+2) {
            Integer ingredientID = ingredientRepository.findByName(tokens[i]);
            ingr = new IngredientsInOfferDTO(ingredientID,
                    Double.parseDouble(tokens[i+1]));
            listIngredientsInOffer.add(ingr);
        }
        return listIngredientsInOffer;
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


        return returnAvailableOffersInRestaurant(restOffs, id);


    }

    private List<AvailableOffers> returnAvailableOffersInRestaurant(List<Restaurant_offer> restOffs, int id) {
        List<AvailableOffers> availableOffers = new ArrayList<>();
        AvailableOffers availableOffersOne = null;
        double quantityAvailable = 0;
        double quantity = 0;
        for (Restaurant_offer offs : restOffs) {
            Set<IngredientsInOffer> ingredientsInOffer = new HashSet<>();

            for (Ingredients ingre : offs.getIngredients()) {
                IngredientsInOffer ingredientsInOffer1 = new IngredientsInOffer(ingre.getIngredient_name());
                    for (Restaurant_offer_ingredients roi : ingre.getRestaurant_offer_ingredients()) {
                     if (roi.getId_restaurant_offer() == offs.getId()) {
                         quantity = roi.getQuantity();
                        for (Restaurant rst : ingre.getRestaurants())
                             if (rst.getId_restaurant() == id) {
                                 for (Available_ingredients avg : rst.getAvailable_ingredients()) {
                                     if (avg.getId_ingredients() == ingre.getId_ingredient()) {
                                      quantityAvailable = avg.getQuantityAvailable();
                                        ingredientsInOffer1 =
                                                new IngredientsInOffer(ingre.getIngredient_name(), ingre.getPurchase_price(), ingre.getQuantity_measure(),
                                                        quantity, quantityAvailable);
                                    }
                                }
                            }
                    }
                }
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
        double quantity = 0.0;
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
                                            quantity, 0.0);
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