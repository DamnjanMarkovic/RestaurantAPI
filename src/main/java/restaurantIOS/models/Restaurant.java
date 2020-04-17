package restaurantIOS.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "restaurants")
public class Restaurant {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id_restaurant;
        private String name_restaurant;
        private String street;
        private Integer number;
        private String city;
        private String image;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_restaurant")
    private Set<Available_ingredients> available_ingredients;

    public Restaurant(Integer id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public Restaurant() {
    }


    public Set<Available_ingredients> getAvailable_ingredients() {
        return available_ingredients;
    }

    public void setAvailable_ingredients(Set<Available_ingredients> available_ingredients) {
        this.available_ingredients = available_ingredients;
    }

    @Override
    public String toString() {
        return "Restaurant with " ;
    }

    public Integer getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(Integer id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public String getName_restaurant() {
        return name_restaurant;
    }

    public void setName_restaurant(String name_restaurant) {
        this.name_restaurant = name_restaurant;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
