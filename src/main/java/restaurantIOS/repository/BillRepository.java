package restaurantIOS.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import restaurantIOS.models.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE order_offers SET id_bill = ? WHERE id_order= ?",
            nativeQuery = true)
    void insertOrdersForBill(Integer id_bill, int id_order);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO  bill_orders (id_bill, id_order) VALUES (?, ?)",
            nativeQuery = true)
    void insertBillIDInOrders(Integer id_bill, int id_order);


}
