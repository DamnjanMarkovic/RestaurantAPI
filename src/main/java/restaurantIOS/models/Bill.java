package restaurantIOS.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bill")
    private Integer id_bill;
    private Integer id_dinning_table;
    private LocalDateTime bill_time;
    private Integer id_user;
    private String payment_type;
    private Double reduction;
    private Double total_amount;
    private Integer id_restaurant;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "bill_orders", joinColumns = @JoinColumn(name = "id_bill"), inverseJoinColumns = @JoinColumn(name = "id_order"))
    private Set<Orders> orders;

    public Bill(Integer id_dinning_table, Integer id_user, String payment_type, Double reduction, Integer id_restaurant, Set<Orders> orders) {
        this.id_dinning_table = id_dinning_table;
        this.id_user = id_user;
        this.payment_type = payment_type;
        this.reduction = reduction;
        this.id_restaurant = id_restaurant;
        this.orders = orders;
    }

    public Bill(Integer id_dinning_table, LocalDateTime bill_time, Integer id_user, String payment_type, Double reduction, Double total_amount, Integer id_restaurant) {
        this.id_dinning_table = id_dinning_table;
        this.bill_time = bill_time;
        this.id_user = id_user;
        this.payment_type = payment_type;
        this.reduction = reduction;
        this.total_amount = total_amount;
        this.id_restaurant = id_restaurant;
    }

    public Bill(Integer id_bill, Integer id_dinning_table, LocalDateTime bill_time, Integer id_user, String payment_type, Double reduction, Double total_amount, Integer id_restaurant) {
        this.id_bill = id_bill;
        this.id_dinning_table = id_dinning_table;
        this.bill_time = bill_time;
        this.id_user = id_user;
        this.payment_type = payment_type;
        this.reduction = reduction;
        this.total_amount = total_amount;
        this.id_restaurant = id_restaurant;
    }

    public Bill() {

    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public Integer getId_bill() {
        return id_bill;
    }

    public void setId_bill(Integer id_bill) {
        this.id_bill = id_bill;
    }

    public Integer getId_dinning_table() {
        return id_dinning_table;
    }

    public void setId_dinning_table(Integer id_dinning_table) {
        this.id_dinning_table = id_dinning_table;
    }

    public LocalDateTime getBill_time() {
        return bill_time;
    }

    public void setBill_time(LocalDateTime bill_time) {
        this.bill_time = bill_time;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public Double getReduction() {
        return reduction;
    }

    public void setReduction(Double reduction) {
        this.reduction = reduction;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public Integer getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(Integer id_restaurant) {
        this.id_restaurant = id_restaurant;
    }
}
