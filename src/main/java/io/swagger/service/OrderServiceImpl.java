package io.swagger.service;

import io.swagger.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order addOrder(Order order) {
        Order o= order;
        System.out.println("uuu"+o);
        return orderRepository.save(order);
    }

    @Override
    public Order deleteOrderById(long id) {
        Optional<Order> os = orderRepository.findById(id);
        if(os.isPresent()) orderRepository.deleteById(id);
        return os.get();
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order findOrderById(long id) {
        Optional<Order> os = orderRepository.findById(id);
        if (os.isPresent()) return os.get();
        else return null;
    }
}
