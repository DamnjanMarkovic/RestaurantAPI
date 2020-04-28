package restaurantIOS.models.dto;

import java.io.Serializable;

public class Restaurant_offer_ingredientDTO implements Serializable {


    private Integer id_ingredient;
    private double quantity;


    public Restaurant_offer_ingredientDTO(Integer id_ingredient, double quantity) {
        this.id_ingredient = id_ingredient;
        this.quantity = quantity;
    }

    public Integer getId_ingredient() {
        return id_ingredient;
    }

    public void setId_ingredient(Integer id_ingredient) {
        this.id_ingredient = id_ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
