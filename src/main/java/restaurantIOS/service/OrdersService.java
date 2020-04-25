package restaurantIOS.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.cfg.annotations.ResultsetMappingSecondPass;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restaurantIOS.models.DinningTable;
import restaurantIOS.models.Ingredients;
import restaurantIOS.models.Orders;
import restaurantIOS.models.dto.IngredientsInOfferDTO;
import restaurantIOS.repository.OrdersRespository;
import restaurantIOS.repository.Restaurant_offerRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@Service
public class OrdersService {

    private final OrdersRespository ordersRespository;
    private final Restaurant_offerRepository restaurant_offerRepository;

    public OrdersService(OrdersRespository ordersRespository, Restaurant_offerRepository restaurant_offerRepository) {
        this.ordersRespository = ordersRespository;
        this.restaurant_offerRepository = restaurant_offerRepository;
    }

    @Transactional
    public String save(List<Orders> orders) throws SQLException, IOException {
        String result = null;
         for (Orders ord:orders             ) {
            ordersRespository.save(ord);
            reduceIngredientsOnOrder(ord);


        }
        result = "Order inserted in the DB";
        return result;
    }

    private void reduceIngredientsOnOrder(Orders ord) throws IOException {

        Integer id_ingredient = 0;
        Double quantity = 0.0;

        List<List<String>> listStringPristigla = restaurant_offerRepository.getIngredientsInOffer(ord.getId_offer());
        for (List<String> lista: listStringPristigla             ) {
            String [] spliter = lista.toString().split(",");
            String id_Inged = spliter[0].substring(1);
            String quanty = spliter[1].substring(0, spliter[1].length() - 1);
            id_ingredient = Integer.parseInt(id_Inged);
            quantity = Double.parseDouble(quanty);
            double reducingQuantity = (quantity) * (ord.getQuantity());
            ordersRespository.reduceIngredientsInOffer(reducingQuantity, ord.getId_restaurant(), id_ingredient);
        }
    }

    @Transactional
    public List<Orders> getOpenOrders(Integer id_dinningTable) {
        Set<Integer> openOrdersIDs = ordersRespository.getOpenOrdersIDs(id_dinningTable);
        return ordersRespository.findAllById(openOrdersIDs);


    }



}
