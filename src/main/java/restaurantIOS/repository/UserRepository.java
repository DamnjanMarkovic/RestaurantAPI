package restaurantIOS.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import restaurantIOS.models.DinningTable;
import restaurantIOS.models.Images;
import restaurantIOS.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserName(String userName);

    @Query("SELECT u.id from User u where u.userFirstName =(:userFirstName)")
    Integer getSpecificUser(String userFirstName);

    @Modifying
    @Query(value = "DELETE FROM user_restaurant WHERE id_user = ?",
            nativeQuery = true)
    void deleteUser_Roles(int id_user);

    @Modifying
    @Query(value = "DELETE FROM user_roles WHERE id_user = ?",
            nativeQuery = true)
    void deleteUser_Restaurant(int id_user);






    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_restaurant (id_restaurant, id_user) " +
                    "VALUES (?, ?)", nativeQuery = true)
    void connectUserAndRestaurant(Integer id_restaurant, Integer id_user);

    @Query(value = "SELECT role.id_role FROM role WHERE role.role_label = ?",
            nativeQuery = true)
    Integer getIDRoleBasedOnRole(String role);


    @Query(value = "SELECT user.id_image FROM user",
            nativeQuery = true)
    List<Integer> getUsersIDs();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_roles (id_user, id_role) VALUES (?, ?)",
            nativeQuery = true)
    void connectUserAndRoles(Integer id_user, Integer id_role);


}
