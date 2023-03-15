package com.product.api.service;

import com.product.api.domain.ProductRequest;
import com.product.api.domain.ProductResponse;
import com.product.api.entity.ProductTest;
import com.product.api.repository.ProductRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public ProductResponse getProduct(int id) {
        Optional<ProductTest> productOptional = repository.findById(id);
        if(productOptional.isPresent()) {
            ProductTest product = productOptional.get();
            StringBuffer pData = new StringBuffer();
            try {
                InputStream inputStream = product.getProductdata().getBinaryStream();
                pData = pData.append(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
            } catch (SQLException se) {
                System.out.println("SQLException occurred::" + se.getMessage());
            } catch (IOException ie) {
                System.out.println("IOException occurred::" + ie.getMessage());
            }

            return ProductResponse.builder().id(product.getId()).customerId(product.getCustomerId())
                    .productData(pData.toString()).build();
        } else {
            return ProductResponse.builder().build();
        }
    }

    public String saveProductData(ProductRequest request) {
        try {

            Blob productData = new SerialBlob(Files.readAllBytes(Paths.get(request.getFileName())));
            ProductTest productTest = new ProductTest();
            productTest.setCustomerId(request.getCustomerId());
            productTest.setProductdata(productData);
            ProductTest pTest = repository.save(productTest);

        } catch (SQLException se) {
            String.format("SqlExp occured while saving data in Product repository::%s%", se.getMessage());
        } catch (IOException ie) {
            System.out.println(" Cannot read data from file ::" + request.getFileName() + ". \n Exception info :: "
                    + ie.getMessage());
        }
        return "Product data saved successfully in Product repository";
    }
}
        /*
        BufferedReader br=null;
        try {
            FileReader fr = new FileReader(request.getFileName());   //reads the file
            br = new BufferedReader(fr);
            while (br.readLine() != null) {

                Blob productData = new SerialBlob(br.readLine().getBytes());
                ProductTest productTest = new ProductTest();
                productTest.setCustomerId(request.getCustomerId());
                productTest.setProductdata(productData);
                repository.save(productTest);
            }
        } catch (SQLException se) {
            System.out.println("SqlExp::" + se.getMessage());
        } catch (IOException ie) {
            System.out.println("IllegalArgumentException occurred::" + ie.getMessage());
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
         */
        //Scanner sc=new Scanner(fis);    //file to be scanned
        /*while(sc.hasNextLine()) {
            try {
                Blob productData = new SerialBlob(sc.nextLine().getBytes());
                ProductTest productTest = new ProductTest();
                productTest.setCustomerId(request.getCustomerId());
                productTest.setProductdata(productData);
                repository.save(productTest);
            } catch (SQLException se) {
                System.out.println("SqlExp::" + se.getMessage());
            } catch (IllegalArgumentException ie) {
                System.out.println("IllegalArgumentException occurred::" + ie.getMessage());
            } finally {
                sc.close();
            }
        }*/

