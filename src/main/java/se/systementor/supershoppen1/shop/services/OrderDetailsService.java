package se.systementor.supershoppen1.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.systementor.supershoppen1.shop.model.OrderDetails;
import se.systementor.supershoppen1.shop.model.OrderDetailsRepository;
import se.systementor.supershoppen1.shop.model.Product;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsService {

    Integer fullPrice;
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    public List<Product> getOrdersById(Integer id) {
        Optional<OrderDetails> maybeOrder = orderDetailsRepository.findById(id);
        if (maybeOrder.isPresent()) {
            OrderDetails order = maybeOrder.get();
            return order.getProducts();
        }
        return null;
    }

    public Integer getFullPrice(Integer id) {
        fullPrice = 0;
        Optional<OrderDetails> maybeOrder = orderDetailsRepository.findById(id);
        if (maybeOrder.isPresent()) {
            OrderDetails order = maybeOrder.get();
            for (Product p : order.getProducts()){
                fullPrice += p.getPrice();

        }
        return fullPrice;
    }
        return null;
}
}
