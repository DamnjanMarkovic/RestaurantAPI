package restaurantIOS.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import restaurantIOS.models.DinningTable;

import java.util.List;
import java.util.Set;


@Repository
public interface DinningTableRepository extends JpaRepository<DinningTable,Integer> {

    @Query("SELECT d from DinningTable d where d.table_number =(:table_number) and " +
            "d.id_restaurant = (:id_restaurant)")
    DinningTable getSpecificDinningTable
            (@Param("table_number") int table_number, @Param("id_restaurant") int id_restaurant);


    @Query(value = "select order_offers.id_table FROM order_offers WHERE (order_offers.id_bill is null or order_offers.id_bill = 0) and order_offers.id_restaurant = ?",
            nativeQuery = true)
    Set<Integer> getOccupiedTables(Integer id_restaurant);
}




