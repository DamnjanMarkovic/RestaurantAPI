package restaurantIOS.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restaurantIOS.models.Orders;
import restaurantIOS.repository.OrdersRespository;

import java.sql.SQLException;
@Service
public class OrdersService {

    private final OrdersRespository ordersRespository;

    public OrdersService(OrdersRespository ordersRespository) {
        this.ordersRespository = ordersRespository;
    }

    @Transactional
    public String save(Orders orders) throws SQLException {
        String result = null;
        ordersRespository.save(orders);

        result = "Order inserted in the DB";
        return result;
    }

}
