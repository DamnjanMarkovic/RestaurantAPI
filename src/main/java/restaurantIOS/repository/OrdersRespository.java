package restaurantIOS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import restaurantIOS.models.Orders;

import java.util.List;
import java.util.Set;

public interface OrdersRespository extends JpaRepository<Orders, Integer> {

    @Query(value = "SELECT order_offers.id_order FROM order_offers WHERE (order_offers.id_bill is null or order_offers.id_bill = 0) and order_offers.id_table = ?",
            nativeQuery = true)
    Set<Integer> getOpenOrdersIDs(Integer id_table);


}
/*
SELECT order_offers.id_offer, order_offers.quantity, order_offers.id_table, order_offers.id_restaurant FROM order_offers WHERE (order_offers.id_bill is null or order_offers.id_bill = 0) and order_offers.id_table = ?
 */