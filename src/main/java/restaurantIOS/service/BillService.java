package restaurantIOS.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restaurantIOS.models.Bill;
import restaurantIOS.models.DinningTable;
import restaurantIOS.models.Orders;
import restaurantIOS.models.Restaurant_offer;
import restaurantIOS.repository.BillRepository;
import restaurantIOS.repository.OrdersRespository;
import restaurantIOS.repository.Restaurant_offerRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final Restaurant_offerRepository restaurant_offerRepository;
    private final OrdersRespository ordersRespository;

    public BillService(BillRepository billRepository, Restaurant_offerRepository restaurant_offerRepository, OrdersRespository ordersRespository) {
        this.billRepository = billRepository;
        this.restaurant_offerRepository = restaurant_offerRepository;
        this.ordersRespository = ordersRespository;
    }

    @Transactional
    public Bill loadBill (Bill billArrived) throws SQLException {
        List<Restaurant_offer>listRof = getAllOffersInBill(billArrived);
        LocalDateTime now = LocalDateTime.now();
        Double finalPrice = calculatePrice(billArrived, listRof);
        Bill billFinal = new Bill(billArrived.getId_dinning_table(), now, billArrived.getId_user(), billArrived.getPayment_type(),
                billArrived.getReduction(), finalPrice, billArrived.getId_restaurant());
        Bill billReturned = billRepository.save(billFinal);
        insertOrdersForBill(billReturned, billArrived);
        return billReturned;
    }






    @Transactional
    public List<Bill> getAll(){
        return billRepository.findAll();
    }


    private void insertOrdersForBill(Bill billReturned, Bill billArrived) {
        Set<Integer> openOrders = ordersRespository.getOpenOrdersIDs(billArrived.getId_dinning_table());
        for(Integer orderIDs: openOrders){
            billRepository.insertOrdersForBill(billReturned.getId_bill(), orderIDs);
            billRepository.insertBillIDInOrders(billReturned.getId_bill(), orderIDs);
        }
    }

    private Double calculatePrice(Bill billArrived, List<Restaurant_offer> listRof) {
        Double ordersQuantityPrice = 0.0;
        for (Orders ord:billArrived.getOrders()             ) {
            ordersQuantityPrice = ordersQuantityPrice + ((getPriceOnOfferID(ord.getId_offer(), listRof)) * ord.getQuantity());
        }
        if(billArrived.getPayment_type().equalsIgnoreCase("CREDIT_CARD")) {
            ordersQuantityPrice = ordersQuantityPrice * 0.9;
        }else if(billArrived.getPayment_type().equalsIgnoreCase("CHECK_PAYMENT")) {
            ordersQuantityPrice = ordersQuantityPrice * 0.95;
        }
        return ordersQuantityPrice * billArrived.getReduction();
    }

    private double getPriceOnOfferID(Integer id_offer, List<Restaurant_offer> listRof) {
        double price = 0;
        for (Restaurant_offer rof: listRof             ) {
            if (id_offer.equals(rof.getId())){
                price = rof.getRestaurant_offer_price();
            }
        }
        return price;
    }

    private List<Restaurant_offer> getAllOffersInBill(Bill billArrived) {
        List<Integer>listOrdersID = new ArrayList<>();
        for (Orders ord:billArrived.getOrders()             ) {
            listOrdersID.add(ord.getId_offer());
        }
        return restaurant_offerRepository.findAllById(listOrdersID);
    }



}
