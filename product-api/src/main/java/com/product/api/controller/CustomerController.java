package com.product.api.controller;

import com.product.api.domain.CustomerRequest;
import com.product.api.domain.CustomerResponse;
import com.product.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("customers")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody HttpEntity<String> saveCustomer(@RequestBody CustomerRequest request) {
        return new HttpEntity<>(customerService.saveCustomer(request));
    }
    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody HttpEntity<CustomerResponse> getCustomer(@PathVariable int id) {
        return new HttpEntity<>(customerService.getCustomer(id));
    }

}