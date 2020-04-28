package restaurantIOS.models.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class RestaurantOfferRequest implements Serializable {

    private Integer id_restaurant_offer;
    private String restaurant_offer_name;
    private Double restaurant_offer_price;
    private String offer_type;
    private Integer id_image;
    private List<IngredientsInOfferDTO> listIngredientsInOffer;

    public RestaurantOfferRequest(Integer id_restaurant_offer, String restaurant_offer_name, Double restaurant_offer_price, String offer_type, Integer id_image, List<IngredientsInOfferDTO> listIngredientsInOffer) {
        this.id_restaurant_offer = id_restaurant_offer;
        this.restaurant_offer_name = restaurant_offer_name;
        this.restaurant_offer_price = restaurant_offer_price;
        this.offer_type = offer_type;
        this.id_image = id_image;
        this.listIngredientsInOffer = listIngredientsInOffer;
    }

    public Integer getId_restaurant_offer() {
        return id_restaurant_offer;
    }

    public void setId_restaurant_offer(Integer id_restaurant_offer) {
        this.id_restaurant_offer = id_restaurant_offer;
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

    public List<IngredientsInOfferDTO> getListIngredientsInOffer() {
        return listIngredientsInOffer;
    }

    public void setListIngredientsInOffer(List<IngredientsInOfferDTO> listIngredientsInOffer) {
        this.listIngredientsInOffer = listIngredientsInOffer;
    }
}
