package io.swagger.service;

import io.swagger.model.Order;

import java.util.List;

public interface OrderService {
    public Order addOrder(Order Order);
    public Order deleteOrderById(long rollNo);
    public List<Order> findAllOrders();
    public Order findOrderById(long id);
}
