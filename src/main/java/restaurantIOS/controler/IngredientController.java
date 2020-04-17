package restaurantIOS.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restaurantIOS.models.Ingredients;
import restaurantIOS.service.IngredientService;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/ingredients")
public class IngredientController {


    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping(value = "/all")
    public List<Ingredients> getAll(){
        return ingredientService.getAll();
    }


    @GetMapping("/{id}")
    public List<Ingredients> getIngredients(@PathVariable Integer id){
        return ingredientService.getIngredients(id);
    }

    @GetMapping("/restaurant/{id}")
    public List<Ingredients> getAvailableIngredientsInRestaurant(@PathVariable Integer id){
        return ingredientService.getAvailableIngredientsInRestaurant(id);
    }

    @PostMapping(value = "/load")
    public List<Ingredients>persist(@RequestBody final Ingredients ingredients){

        ingredientService.save(ingredients);
        return ingredientService.getAll();


    }



}
