package restaurantIOS.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "restaurant_offer")
//@NamedQuery(name = "Restaurant_offer.getAvailableOffersInRestaurant",
  //      query = "SELECT o from Restaurant_offer o inner JOIN o.ingredients i inner JOIN i.restaurants r where r.id_restaurant =?1")
/*
@NamedQuery(name = "Restaurant_offer.getAvailableOffersInRestaurant",
        query = "select restaurant_offer.id_restaurant_offer, restaurant_offer.restaurant_offer_name,restaurant_offer.restaurant_offer_price, " +
        "restaurant_offer.offer_type, restaurant_offer.image, ingredients.ingredient_name, ingredients.purchase_price, " +
        "ingredients.quantity_measure, restaurant_offer_ingredients.quantity from restaurant_offer_ingredients INNER JOIN " +
        "restaurant_offer ON restaurant_offer.id_restaurant_offer = restaurant_offer_ingredients.id_restaurant_offer INNER JOIN " +
        "ingredients on ingredients.id_ingredient = restaurant_offer_ingredients.id_ingredient ORDER by restaurant_offer.restaurant_offer_name",
        nativeQuery = true)*/

public class Restaurant_offer {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_restaurant_offer")
        private Integer id;
        private String restaurant_offer_name;
        private Double restaurant_offer_price;
        private String offer_type;
        private String image;

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinTable(name = "restaurant_offer_ingredients", joinColumns = @JoinColumn(name = "id_restaurant_offer"), inverseJoinColumns = @JoinColumn(name = "id_ingredient"))
        private Set<Ingredients> ingredients;


    public Restaurant_offer() {
    }

    public Set<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public Restaurant_offer(String restaurant_offer_name) {
        this.restaurant_offer_name = restaurant_offer_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRestaurant_offer_name() {
        return restaurant_offer_name;
    }

    public void setRestaurant_offer_name(String restaurant_offer_name) {
        this.restaurant_offer_name = restaurant_offer_name;
    }

    public Double getRestaurant_offer_price() {
        return restaurant_offer_price;
    }

    public void setRestaurant_offer_price(Double restaurant_offer_price) {
        this.restaurant_offer_price = restaurant_offer_price;
    }

    public String getOffer_type() {
        return offer_type;
    }

    public void setOffer_type(String offer_type) {
        this.offer_type = offer_type;
    }
}
