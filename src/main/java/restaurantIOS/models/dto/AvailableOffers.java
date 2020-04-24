package restaurantIOS.models.dto;

import restaurantIOS.models.Ingredients;

import java.io.Serializable;
import java.util.Set;

public class AvailableOffers implements Serializable {

    private Integer idOffer;
    private String restaurant_offer_name;
    private Double restaurant_offer_price;
    private String offer_type;
    private Integer id_image;

    private Set<IngredientsInOffer> ingredientsInOffer;



    public AvailableOffers(Integer idOffer, String restaurant_offer_name, Double restaurant_offer_price,
                           String offer_type, Integer id_image, Set<IngredientsInOffer> ingredientsInOffer) {
        this.idOffer = idOffer;
        this.restaurant_offer_name = restaurant_offer_name;
        this.restaurant_offer_price = restaurant_offer_price;
        this.offer_type = offer_type;
        this.id_image = id_image;

        this.ingredientsInOffer = ingredientsInOffer;

    }



    public Set<IngredientsInOffer> getIngredientsInOffer() {
        return ingredientsInOffer;
    }

    public void setIngredientsInOffer(Set<IngredientsInOffer> ingredientsInOffer) {
        this.ingredientsInOffer = ingredientsInOffer;
    }

    public Integer getIdOffer() {
        return idOffer;
    }

    public void setIdOffer(Integer idOffer) {
        this.idOffer = idOffer;
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

    public Integer getId_image() {
        return id_image;
    }

    public void setId_image(Integer id_image) {
        this.id_image = id_image;
    }
}
