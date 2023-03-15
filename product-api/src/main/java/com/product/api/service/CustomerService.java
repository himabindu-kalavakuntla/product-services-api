package com.product.api.service;

import com.product.api.domain.CustomerRequest;
import com.product.api.domain.CustomerResponse;
import com.product.api.entity.Customer;
import com.product.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;
    public CustomerResponse getCustomer(int id) {
        Optional<Customer> customerOptional = repository.findById(id);
        if(customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            return CustomerResponse.builder().id(customer.getId()).firstname(customer.getFirstname())
                    .lastname(customer.getLastname()).build();
        }
        return CustomerResponse.builder().build();
    }

    public String saveCustomer(CustomerRequest request) throws IllegalArgumentException {
        Customer customer = new Customer(request.getFirstName(), request.getLastName());
        Customer data = repository.save(customer);
        return "Customer saved successfully : " + data.getId();
    }
}
