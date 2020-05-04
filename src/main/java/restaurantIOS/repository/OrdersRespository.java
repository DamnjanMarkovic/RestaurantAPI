package restaurantIOS.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import restaurantIOS.models.Orders;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OrdersRespository extends JpaRepository<Orders, Integer> {

    @Query(value = "SELECT order_offers.id_order FROM order_offers WHERE (order_offers.id_bill is null or order_offers.id_bill = 0) and order_offers.id_table = ?",
            nativeQuery = true)
    Set<Integer> getOpenOrdersIDs(Integer id_table);

    @Query(value = "SELECT id_ingredient, quantity FROM restaurant_offer_ingredients WHERE id_restaurant_offer = ?",
            nativeQuery = true)
    List<Map<Integer, Double>> getIngredientsInOffer(int id_offer);


    @Modifying
    @Transactional
    @Query(value = "UPDATE available_ingredients SET available_ingredients.quantityAvailable = " +
            "available_ingredients.quantityAvailable - ? WHERE available_ingredients.id_restaurant = ? " +
            "AND available_ingredients.id_ingredients = ?", nativeQuery = true)
    void reduceIngredientsInOffer(double value, Integer id_restaurant, Integer id_ingredients);


}
