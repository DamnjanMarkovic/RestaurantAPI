package restaurantIOS.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import restaurantIOS.models.Ingredients;
import restaurantIOS.repository.IngredientRepository;

import java.util.Collections;
import java.util.List;

@Service
public class IngredientService {

    private IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Transactional
    public List<Ingredients> getAll(){
        return ingredientRepository.findAll();
    }

    @Transactional
    public List<Ingredients> getIngredients( Integer id){
        return ingredientRepository.findAllById(Collections.singleton(id));
    }
    @Transactional
    public List<Ingredients> getAvailableIngredientsInRestaurant(Integer id){
        return ingredientRepository.getAvailableIngredientsInRestaurant(id);
    }


    @Transactional
    public List<Ingredients>save(Ingredients ingredients){
        ingredientRepository.save(ingredients);
        return ingredientRepository.findAll();
    }





}
