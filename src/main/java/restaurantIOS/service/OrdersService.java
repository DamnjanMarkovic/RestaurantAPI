package restaurantIOS.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restaurantIOS.models.DinningTable;
import restaurantIOS.models.Orders;
import restaurantIOS.repository.OrdersRespository;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Service
public class OrdersService {

    private final OrdersRespository ordersRespository;

    public OrdersService(OrdersRespository ordersRespository) {
        this.ordersRespository = ordersRespository;
    }

    @Transactional
    public String save(List<Orders> orders) throws SQLException {
        String result = null;
         for (Orders ord:orders             ) {
            ordersRespository.save(ord);
        }
        result = "Order inserted in the DB";
        return result;
    }

    @Transactional
    public List<Orders> getOpenOrders(Integer id_dinningTable) {
        Set<Integer> openOrdersIDs = ordersRespository.getOpenOrdersIDs(id_dinningTable);
        return ordersRespository.findAllById(openOrdersIDs);


    }




}
