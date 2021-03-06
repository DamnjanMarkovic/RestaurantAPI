package restaurantIOS.service;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import restaurantIOS.models.Available_ingredients;
import restaurantIOS.models.Images;
import restaurantIOS.models.Ingredients;
import restaurantIOS.models.Restaurant;
import restaurantIOS.models.dto.IngredientsInOffer;
import restaurantIOS.models.dto.IngredientsRequest;
import restaurantIOS.repository.IngredientRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Transactional
    public List<IngredientsInOffer> getAll(){
        return prepareListAllIngredients(ingredientRepository.findAll());
    }

    private List<IngredientsInOffer> prepareListAllIngredients(List<Ingredients> all) {
        IngredientsInOffer finalIngred = null;
        List<IngredientsInOffer> listFinal = new ArrayList<>();
        double quantityAvailableFinal = 0.0;

        for (Ingredients ingr:all             ) {
            for (Restaurant rest: ingr.getRestaurants()                 ) {
                for (Available_ingredients ava: rest.getAvailable_ingredients()                     ) {
                    if(ingr.getId_ingredient()==ava.getId_ingredients() &&
                            rest.getId_restaurant() == ava.getId_restaurant()) {
                    quantityAvailableFinal += ava.getQuantityAvailable();
                    }
                }
            }
            finalIngred = new IngredientsInOffer(ingr.getIngredient_name(),
                    ingr.getPurchase_price(), ingr.getQuantity_measure(),
                    quantityAvailableFinal);
            quantityAvailableFinal = 0.0;
            listFinal.add(finalIngred);
        }
        return listFinal;
    }

    @Transactional
    public List<Ingredients> getIngredients( Integer id){
        return ingredientRepository.findAllById(Collections.singleton(id));
    }
    @Transactional
    public List<IngredientsInOffer> getAvailableIngredientsInRestaurant(Integer id){
        //return ingredientRepository.getAvailableIngredientsInRestaurant(id);
        return prepareListIngredients(ingredientRepository.getAvailableIngredientsInRestaurant(id), id);
    }

    private List<IngredientsInOffer> prepareListIngredients(List<Ingredients> availableIngredientsInRestaurant, Integer id) {
    IngredientsInOffer finalIngred = null;
    List<IngredientsInOffer> listFinal = new ArrayList<>();

        for (Ingredients ingr:availableIngredientsInRestaurant             ) {
            for (Restaurant rest: ingr.getRestaurants()                 ) {
                for (Available_ingredients ava: rest.getAvailable_ingredients()                     ) {
                   if(ingr.getId_ingredient()==ava.getId_ingredients() &&
                   rest.getId_restaurant() == ava.getId_restaurant() && rest.getId_restaurant() == id) {

                       finalIngred = new IngredientsInOffer(ingr.getIngredient_name(),
                               ingr.getPurchase_price(), ingr.getQuantity_measure(),
                               ava.getQuantityAvailable());
                   }
                }
            }
            listFinal.add(finalIngred);
        }
        return listFinal;
    }


    @Transactional
    public List<Ingredients>save(IngredientsRequest ingredientsRequest){
        Ingredients ingredientArrived = new Ingredients();
        ingredientArrived.setIngredient_name(ingredientsRequest.getIngredient_name());
        Example<Ingredients> ingredientArrivedExample = Example.of(ingredientArrived);

        if (ingredientRepository.checkIngredientexistance(ingredientsRequest.getIngredient_name()) > 0) {
            Integer ingredientArrivedID = ingredientRepository.findByName(ingredientsRequest.getIngredient_name());
            Integer existanceQuantity = ingredientRepository.checkIfExistInRestaurant(ingredientsRequest.getId_restaurant(), ingredientArrivedID);
            if (existanceQuantity>0) {
                ingredientRepository.updateAvailableIngredients(ingredientsRequest.getQuantityUpdating(), ingredientsRequest.getId_restaurant(),
                        ingredientArrivedID);
            } else {
                ingredientRepository.insertAvailableIngredients(ingredientArrivedID,
                        ingredientsRequest.getId_restaurant(), ingredientsRequest.getQuantityUpdating());
            }
        } else {
            Ingredients ingredients = new Ingredients(ingredientsRequest.getIngredient_name(),
                    ingredientsRequest.getPurchase_price(), ingredientsRequest.getQuantity_measure());
            Ingredients ingredientsNew = ingredientRepository.save(ingredients);
            ingredientRepository.insertAvailableIngredients(ingredientsNew.getId_ingredient(),
                    ingredientsRequest.getId_restaurant(), ingredientsRequest.getQuantityUpdating());
        }
        return ingredientRepository.findAll();
    }
}


