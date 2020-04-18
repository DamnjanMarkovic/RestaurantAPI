package restaurantIOS.models;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;


@Entity
@Table(name = "available_ingredients")
public class Available_ingredients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_ingredientsAvailable;
    private int id_restaurant;
    private int id_ingredients;
    private double quantityAvailable;

    public Available_ingredients() {
    }

    public int getId_ingredientsAvailable() {
        return id_ingredientsAvailable;
    }

    public void setId_ingredientsAvailable(int id_ingredientsAvailable) {
        this.id_ingredientsAvailable = id_ingredientsAvailable;
    }

    public int getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(int id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public int getId_ingredients() {
        return id_ingredients;
    }

    public void setId_ingredients(int id_ingredients) {
        this.id_ingredients = id_ingredients;
    }

    public double getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(double quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }
}
