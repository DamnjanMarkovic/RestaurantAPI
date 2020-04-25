package restaurantIOS.models.dto;


import java.io.Serializable;


public class IngredientsInOffer implements Serializable {


    private String ingredient_name;
    private double purchase_price;
    private String quantity_measure;
    private double quantity;
    //private Set<Available_ingredients> available_ingredients;
    private double quantityAvailable;
    public IngredientsInOffer(String ingredient_name, double purchase_price, String quantity_measure, double quantity, double quantityAvailable) {
        this.ingredient_name = ingredient_name;
        this.purchase_price = purchase_price;
        this.quantity_measure = quantity_measure;
        this.quantity = quantity;
        this.quantityAvailable = quantityAvailable;

    }

    public IngredientsInOffer(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public IngredientsInOffer() {

    }

    public double getQuantity() {
        return quantity;
    }

    public double getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(double quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
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
