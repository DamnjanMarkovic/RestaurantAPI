package restaurantIOS.models;

import javax.persistence.*;

@Entity
@Table(name = "order_offers")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private int id_order;
    private int id_offer;
    private double quantity;
    private int id_table;
    private int id_bill;
    private int id_user_deleted;
    private int id_restaurant;

    public Orders(int id_offer, double quantity, int id_table, int id_restaurant) {
        this.id_offer = id_offer;
        this.quantity = quantity;
        this.id_table = id_table;
        this.id_restaurant = id_restaurant;
    }




    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public int getId_offer() {
        return id_offer;
    }

    public void setId_offer(int id_offer) {
        this.id_offer = id_offer;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getId_table() {
        return id_table;
    }

    public void setId_table(int id_table) {
        this.id_table = id_table;
    }

    public int getId_bill() {
        return id_bill;
    }

    public void setId_bill(int id_bill) {
        this.id_bill = id_bill;
    }

    public int getId_user_deleted() {
        return id_user_deleted;
    }

    public void setId_user_deleted(int id_user_deleted) {
        this.id_user_deleted = id_user_deleted;
    }

    public int getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(int id_restaurant) {
        this.id_restaurant = id_restaurant;
    }
}