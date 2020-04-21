package restaurantIOS.controler;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restaurantIOS.models.Orders;
import restaurantIOS.service.OrdersService;

import javax.validation.Valid;
import java.sql.SQLException;

@RestController
@RequestMapping(value = "/rest/orders")
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping(value = "/load")
    public String loadOrder (@Valid @RequestBody Orders orders){
        String result = null;
        String response = null;
        try {
            response = ordersService.save(orders);
            result = response;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


}
