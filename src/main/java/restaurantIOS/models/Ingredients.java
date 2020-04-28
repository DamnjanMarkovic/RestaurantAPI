package restaurantIOS.models;

import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ingredients")
@NamedQuery(name = "Ingredient.getAvailableIngredientsInRestaurant",
        query = "SELECT i from Ingredients i JOIN i.restaurants r where r.id_restaurant =?1")

public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_ingredient;
    private String ingredient_name;
    private double purchase_price;
    private String quantity_measure;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "available_ingredients", joinColumns = @JoinColumn(name = "id_ingredients"), inverseJoinColumns = @JoinColumn(name = "id_restaurant"))
    private Set<Restaurant> restaurants;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_ingredient")
    private Set<Restaurant_offer_ingredients> restaurant_offer_ingredients;

    public Ingredients(String ingredient_name, double purchase_price, String quantity_measure) {
        this.ingredient_name = ingredient_name;
        this.purchase_price = purchase_price;
        this.quantity_measure = quantity_measure;
    }

    public Set<Restaurant_offer_ingredients> getRestaurant_offer_ingredients() {
        return restaurant_offer_ingredients;
    }

    public void setRestaurant_offer_ingredients(Set<Restaurant_offer_ingredients> restaurant_offer_ingredients) {
        this.restaurant_offer_ingredients = restaurant_offer_ingredients;
    }

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public Ingredients() {
    }

    public int getId_ingredient() {
        return id_ingredient;
    }

    public void setId_ingredient(int id_ingredient) {
        this.id_ingredient = id_ingredient;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public double getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(double purchase_price) {
        this.purchase_price = purchase_price;
    }

    public String getQuantity_measure() {
        return quantity_measure;
    }

    public void setQuantity_measure(String quantity_measure) {
        this.quantity_measure = quantity_measure;
    }
}




