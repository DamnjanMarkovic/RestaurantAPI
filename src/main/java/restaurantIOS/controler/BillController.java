package restaurantIOS.controler;

import org.springframework.web.bind.annotation.*;
import restaurantIOS.models.Bill;
import restaurantIOS.models.DinningTable;
import restaurantIOS.service.BillService;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping(value = "/load")
    public Bill loadBill (@Valid @RequestBody Bill billArrived) throws SQLException {
        return billService.loadBill(billArrived);
    }

    @GetMapping(value = "/all")
    public List<Bill> getAll(){
        return billService.getAll();
    }



}
