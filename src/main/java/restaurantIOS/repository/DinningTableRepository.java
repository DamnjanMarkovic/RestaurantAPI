package restaurantIOS.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import restaurantIOS.models.DinningTable;


@Repository
public interface DinningTableRepository extends JpaRepository<DinningTable,Integer> {

    @Query("SELECT d from DinningTable d where d.table_number =(:table_number) and " +
            "d.id_restaurant = (:id_restaurant)")
    DinningTable getSpecificDinningTable
            (@Param("table_number") int table_number, @Param("id_restaurant") int id_restaurant);













}




