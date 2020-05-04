package restaurantIOS.controler;

import org.springframework.web.bind.annotation.*;
import restaurantIOS.models.DinningTable;
import restaurantIOS.models.Orders;
import restaurantIOS.service.OrdersService;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/orders")
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping(value = "/load")
    public String loadOrder (@Valid @RequestBody List<Orders> orders){
        String result = null;
        String response = null;
        try {
            response = ordersService.save(orders);
            result = response;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/getOpenOrders/{id_dinningTable}")
    public List<Orders> getOpenOrders(@PathVariable Integer id_dinningTable){
        return ordersService.getOpenOrders(id_dinningTable);
    }

}
