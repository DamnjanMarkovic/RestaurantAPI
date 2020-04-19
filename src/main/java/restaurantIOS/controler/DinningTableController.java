package restaurantIOS.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restaurantIOS.models.DinningTable;
import restaurantIOS.service.DinningTableService;
import restaurantIOS.service.IngredientService;

import java.util.List;


@RestController
@RequestMapping(value = "/rest/dinningTable")
public class DinningTableController {


    private DinningTableService dinningTableService;

    public DinningTableController(DinningTableService dinningTableService) {
        this.dinningTableService = dinningTableService;
    }

    @GetMapping(value = "/all")
    public List<DinningTable> getAll(){
        return dinningTableService.getAll();
    }


    @GetMapping("/{id}")
    public List<DinningTable> getDinningTable(@PathVariable Integer id){
        return dinningTableService.getDinningTable(id);
    }

    @GetMapping("/getOccupiedTables/{id_restaurant}")
    public List<DinningTable> getOccupiedTables(@PathVariable Integer id_restaurant){
        return dinningTableService.getOccupiedTables(id_restaurant);
    }



    @PostMapping(value = "/load")
    public List<DinningTable>persist(@RequestBody final DinningTable dinningTable){

        dinningTableService.save(dinningTable);
        return dinningTableService.getAll();


    }

    @GetMapping(value = "/tableID/{table_number}/{id_restaurant}")
    public DinningTable getSpecificDinningTable(
                        @PathVariable("table_number") int table_number,
                         @PathVariable("id_restaurant") int id_restaurant) {
        return dinningTableService.getSpecificDinningTable(table_number, id_restaurant);
    }


}
