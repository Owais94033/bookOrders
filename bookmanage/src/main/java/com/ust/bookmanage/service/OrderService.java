package com.ust.bookmanage.service;

import com.ust.bookmanage.Repository.BookRepository;
import com.ust.bookmanage.Repository.CustomerRepository;
import com.ust.bookmanage.Repository.OrderRepository;
import com.ust.bookmanage.model.Book;
import com.ust.bookmanage.model.Customers;
import com.ust.bookmanage.model.Order;
import com.ust.bookmanage.service.client.BookClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookClient bookClient;

//    Implement a Feign Client in the Order Service to
//    communicate with the Book Service to check stock availability.
    public Order placeOrder(Order order){
        Book book = bookClient.getBookById(order.getBookId());

        if(book!=null && book.getStock() > order.getQuantity()){
            book.setStock(book.getStock() - order.getQuantity());
            return orderRepository.save(order);
        }
        throw new RuntimeException("Cant place the order. try choosing minimum quantity");

    }
    

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order order) {
        order.setId(id);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
