package restaurantIOS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurantIOS.models.Orders;

public interface OrdersRespository extends JpaRepository<Orders, Integer> {
}
