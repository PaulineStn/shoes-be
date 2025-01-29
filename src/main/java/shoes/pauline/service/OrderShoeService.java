package shoes.pauline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoes.pauline.entity.OrderShoe;
import shoes.pauline.repository.OrderShoeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderShoeService {

    @Autowired
    private OrderShoeRepository orderShoeRepository;

    public List<OrderShoe> getAllOrderShoes() {
        return orderShoeRepository.findAll();
    }

    public Optional<OrderShoe> getOrderShoeById(Long id) {
        return orderShoeRepository.findById(id);
    }

    public OrderShoe saveOrderShoe(OrderShoe orderShoe) {
        return orderShoeRepository.save(orderShoe);
    }

    public void deleteOrderShoe(Long id) {
        orderShoeRepository.deleteById(id);
    }
}