package restaurantIOS.models.dto;

public class IngredientsRequest {


    private String ingredient_name;
    private double purchase_price;
    private String quantity_measure;
    private Integer id_restaurant;
    private Double quantityUpdating;

    public IngredientsRequest(String ingredient_name, double purchase_price, String quantity_measure, Integer id_restaurant, Double quantityUpdating) {
        this.ingredient_name = ingredient_name;
        this.purchase_price = purchase_price;
        this.quantity_measure = quantity_measure;
        this.id_restaurant = id_restaurant;
        this.quantityUpdating = quantityUpdating;
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

    public Integer getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(Integer id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public Double getQuantityUpdating() {
        return quantityUpdating;
    }

    public void setQuantityUpdating(Double quantityUpdating) {
        this.quantityUpdating = quantityUpdating;
    }
}
