package org.jdc.restclient.service;

import lombok.Data;
import org.jdc.restclient.ds.Customer;
import org.jdc.restclient.ds.Customers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class CustomerService {

    private static final String BAST_URL = "http://localhost:8080/";

    private RestTemplate restTemplate = new RestTemplate();

    private List<Customer> customers;

    public CustomerService() {
        customers = new ArrayList<>();
        this.customers = listAllCustomers();
    }

    private List<Customer> listAllCustomers() {
        ResponseEntity<Customers> customersResponseEntity = restTemplate.getForEntity(BAST_URL + "list-customers-v2?type=json",
                Customers.class);
        if (customersResponseEntity.getStatusCode().is2xxSuccessful()) {
            return customersResponseEntity.getBody().getCustomers();
        } else
            throw new IllegalStateException("Retrieve list customers error-" +
                    "status code : %s".formatted(customersResponseEntity.getStatusCode()));
    }
}
