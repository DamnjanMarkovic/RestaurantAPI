package restaurantIOS.repository;



import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import restaurantIOS.models.Images;
import restaurantIOS.models.Restaurant_offer;
import org.springframework.data.jpa.repository.JpaRepository;
import restaurantIOS.models.dto.IngredientsInOfferDTO;

import java.util.List;
import java.util.Map;

@Repository
public interface Restaurant_offerRepository extends JpaRepository<Restaurant_offer, Integer> {

    @Query("SELECT o from Restaurant_offer o inner JOIN o.ingredients i inner join i.restaurant_offer_ingredients roi inner JOIN i.restaurants r inner" +
            " join r.available_ingredients z where r.id_restaurant =(:id_restaurant) group by o.id")
    List<Restaurant_offer> getAvailableOffersInRestaurant (int id_restaurant);

    @Query("SELECT o from Restaurant_offer o inner JOIN o.ingredients i inner join i.restaurant_offer_ingredients roi inner JOIN i.restaurants r inner" +
            " join r.available_ingredients z group by o.id")
    List<Restaurant_offer> getAllAvailableOffers();

    @Query(value = "SELECT restaurant_offer.id_image FROM restaurant_offer",
            nativeQuery = true)
    List<Integer> getOffersIDs();

    @Query(value = "SELECT id_ingredient as ingred, quantity as quan FROM restaurant_offer_ingredients WHERE id_restaurant_offer = ?",
            nativeQuery = true)
    List<List<String>> getIngredientsInOffer(int id_offer);


    @Modifying
    @Query(value = "INSERT INTO restaurant_offer_ingredients (id_restaurant_offer, id_ingredient, quantity) VALUES (?,?,?)",
            nativeQuery = true)
    void connectOfferAndIngredients(Integer id, Integer id_ingredient, double quantity);

    @Modifying
    @Query(value = "DELETE FROM restaurant_offer_ingredients WHERE id_restaurant_offer = ?",
            nativeQuery = true)
    void deletePreviousData(Integer id_restaurant_offer);

    @Modifying
    @Query(value = "UPDATE restaurant_offer SET restaurant_offer_name =?, " +
            "restaurant_offer_price =?, offer_type=?, id_image =? WHERE id_restaurant_offer = ?",
            nativeQuery = true)
    void updateOffer(String restaurant_offer_name, Double restaurant_offer_price, String offer_type, Integer id_image, Integer id_restaurant_offer);
}

