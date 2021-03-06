package restaurantIOS.models;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;


@Entity
@Table(name = "restaurant_offer_ingredients")
public class Restaurant_offer_ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_restaurant_offer_ingredients;
    private int id_restaurant_offer;
    private int id_ingredient;
    private double quantity;



    public Restaurant_offer_ingredients() {
    }

    public int getId_restaurant_offer_ingredients() {
        return id_restaurant_offer_ingredients;
    }

    public void setId_restaurant_offer_ingredients(int id_restaurant_offer_ingredients) {
        this.id_restaurant_offer_ingredients = id_restaurant_offer_ingredients;
    }

    public int getId_restaurant_offer() {
        return id_restaurant_offer;
    }

    public void setId_restaurant_offer(int id_restaurant_offer) {
        this.id_restaurant_offer = id_restaurant_offer;
    }

    public int getId_ingredient() {
        return id_ingredient;
    }

    public void setId_ingredient(int id_ingredient) {
        this.id_ingredient = id_ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
