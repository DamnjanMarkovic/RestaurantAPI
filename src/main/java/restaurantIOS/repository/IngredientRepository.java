package restaurantIOS.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Transactional
    @Query(value = "UPDATE available_ingredients SET quantityAvailable = quantityAvailable + ? WHERE id_restaurant = ? and " +
            "id_ingredients = ?",
            nativeQuery = true)
    void updateAvailableIngredients(Double quantityAvailable, Integer id_restaurant, Integer id_ingredient);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO available_ingredients " +
            "(id_ingredients, id_restaurant, quantityAvailable) VALUES (?, ?, ?)",
            nativeQuery = true)
    void insertAvailableIngredients(int id_ingredient, Integer id_restaurant, Double quantityAvailable);

    @Query(value = "select count(*) from available_ingredients WHERE id_restaurant = ? and id_ingredients = ?",
            nativeQuery = true)
    Integer checkIfExistInRestaurant(Integer id_restaurant, int id_ingredients);

    @Query(value = "select count(*) from ingredients WHERE ingredient_name = ?",
            nativeQuery = true)
    Integer checkIngredientexistance(String ingredient_name);

    @Query(value = "select id_ingredient from ingredients WHERE ingredient_name = ?",
            nativeQuery = true)
    Integer findByName(String ingredient_name);
}
