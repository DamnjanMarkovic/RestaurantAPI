package restaurantIOS.models.dto;

public class Restaurant_offer_ingredientDTO {


    private double quantity;

    public Restaurant_offer_ingredientDTO(double quantity) {
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
