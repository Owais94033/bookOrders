package com.ust.bookmanage.service;

import com.ust.bookmanage.Repository.CustomerRepository;
import com.ust.bookmanage.model.Book;
import com.ust.bookmanage.model.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customers> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Optional<Customers> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customers saveCustomer(Customers customers) {
        return customerRepository.save(customers);
    }

    public Customers updateCustomer(Long id, Customers customers) {
        customers.setId(id);
        return customerRepository.save(customers);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }


}
