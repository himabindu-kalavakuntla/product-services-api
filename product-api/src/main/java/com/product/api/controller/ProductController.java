package com.product.api.controller;

import com.product.api.domain.ProductRequest;
import com.product.api.domain.ProductResponse;
import com.product.api.service.ProductService;
import com.product.api.service.ProductSolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("product")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSolrService productSolrService;

    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody HttpEntity<String> saveProduct(@RequestBody ProductRequest request) {
        return new HttpEntity<>(productService.saveProductData(request));
    }
    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody HttpEntity<ProductResponse> getCustomer(@PathVariable int id) {
        return new HttpEntity<>(productService.getProduct(id));
    }

    @RequestMapping(value = "/solr/processData", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody HttpEntity<String> processData(@RequestBody ProductRequest request) {
        return new HttpEntity<String>(productSolrService.processData(request));
    }
}