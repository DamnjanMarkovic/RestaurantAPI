package restaurantIOS.repository;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import restaurantIOS.models.Images;
import restaurantIOS.models.Restaurant_offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
@Repository
public interface Restaurant_offerRepository extends JpaRepository<Restaurant_offer, Integer> {

    @Query("SELECT o from Restaurant_offer o inner JOIN o.ingredients i inner join i.restaurant_offer_ingredients roi inner JOIN i.restaurants r inner" +
            " join r.available_ingredients z where r.id_restaurant =(:id_restaurant) group by o.id")
    List<Restaurant_offer> getAvailableOffersInRestaurant (int id_restaurant);

    @Query("SELECT o from Restaurant_offer o inner JOIN o.ingredients i inner join i.restaurant_offer_ingredients roi inner JOIN i.restaurants r inner" +
            " join r.available_ingredients z group by o.id")
    List<Restaurant_offer> getAllAvailableOffers();

    @Query(value = "SELECT restaurant_offer.image FROM restaurant_offer",
            nativeQuery = true)
    List<Integer> getOffersIDs();



}






/*
    @Query(
            value = "select restaurant_offer.id_restaurant_offer, restaurant_offer.restaurant_offer_name,restaurant_offer.restaurant_offer_price, " +
                    "restaurant_offer.offer_type, restaurant_offer.image, ingredients.ingredient_name, ingredients.purchase_price, ingredients.quantity_measure, " +
                    "restaurant_offer_ingredients.quantity, avg.id_restaurant, avg.quantityAvailable from restaurant_offer_ingredients INNER JOIN " +
                    "restaurant_offer ON restaurant_offer.id_restaurant_offer = restaurant_offer_ingredients.id_restaurant_offer INNER JOIN ingredients " +
                    "on ingredients.id_ingredient = restaurant_offer_ingredients.id_ingredient INNER JOIN available_ingredients avg on avg.id_ingredients = " +
                    "ingredients.id_ingredient INNER JOIN restaurants on restaurants.id_restaurant = avg.id_restaurant where avg.id_restaurant = ? and " +
                    "avg.quantityAvailable>restaurant_offer_ingredients.quantity",
            nativeQuery = true)*/