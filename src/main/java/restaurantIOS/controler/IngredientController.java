package restaurantIOS.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restaurantIOS.models.Ingredients;
import restaurantIOS.models.dto.IngredientsInOffer;
import restaurantIOS.models.dto.IngredientsRequest;
import restaurantIOS.service.IngredientService;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/ingredients")
public class IngredientController {


    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }




    @GetMapping("/{id}")
    public List<Ingredients> getIngredients(@PathVariable Integer id){
        return ingredientService.getIngredients(id);
    }

    @GetMapping(value = "/all")
    public List<IngredientsInOffer> getAll(){
        return ingredientService.getAll();
    }

    @GetMapping("/restaurant/{id}")
    public List<IngredientsInOffer> getAvailableIngredientsInRestaurant(@PathVariable Integer id){
        return ingredientService.getAvailableIngredientsInRestaurant(id);
    }

    @PostMapping(value = "/load")
    public List<IngredientsInOffer>persist(@RequestBody IngredientsRequest ingredientsRequest){
        ingredientService.save(ingredientsRequest);
        return ingredientService.getAll();
    }



}


/*
    @PostMapping(value = "/load")
    public List<Ingredients>persist(@RequestParam("id_restaurant") @PathVariable Integer id_restaurant,
                                    @RequestParam("quantityUpdating") @PathVariable Double quantityUpdating,
                                    @RequestBody Ingredients ingredients){
        System.out.println("nesto");
        ingredientService.save(ingredients, id_restaurant, quantityUpdating);
        return ingredientService.getAll();


    }
 */