package restaurantIOS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import restaurantIOS.models.DinningTable;
import restaurantIOS.models.Ingredients;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredients,Integer> {
    @Query("SELECT i from Ingredients i JOIN i.restaurants r where r.id_restaurant =(:id_restaurant)")
    List<Ingredients> getAvailableIngredientsInRestaurant
            (@Param("id_restaurant") int id_restaurant);
}

/*
    @Query(value = "SELECT ingredients.ingredient_name, available_ingredients.quantityAvailable FROM ingredients join available_ingredients " +
            "on ingredients.id_ingredient = available_ingredients.id_ingredients JOIN restaurants on available_ingredients.id_restaurant = restaurants.id_restaurant " +
            "where restaurants.id_restaurant = ?",ue)
    List<Ingredients> getAvailableIngredientsInRestaurant*/
