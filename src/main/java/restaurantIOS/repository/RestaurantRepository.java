package restaurantIOS.repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import restaurantIOS.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {


    @Modifying
    @Query(value = "INSERT INTO dinning_table(table_number, id_restaurant, capacity) VALUES (?,?,?)",
            nativeQuery = true)
    void insertDinningTables(int i, Integer id_restaurant, int i1);

    @Modifying
    @Query(value = "DELETE FROM available_ingredients WHERE id_restaurant = ?",
            nativeQuery = true)
    void deleteAvailableIngredientsInRestaurant(int id_restaurant);



}
