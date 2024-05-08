package org.jdc.restclient.service;

import org.jdc.restclient.ds.Customer;
import org.jdc.restclient.ds.Customers;
import org.jdc.restclient.exception.CustomerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerService {
    private static final String BAST_URL="http://localhost:8080/";

    private RestTemplate restTemplate=
            new RestTemplate();
    //http://localhost:8080/update-customer?id=1
    public  void saveUpdateCustomer(int id,Customer customer){
        restTemplate.put(BAST_URL + "update-customer?id={id}",customer,id);
    }


    public Customer findCustomerById(int id){
        return this.customers
                .stream()
                .filter( c -> c.getId() == id)
                .findFirst()
                .orElseThrow(()-> new CustomerNotFoundException());
    }


    //'http://localhost:8080/delete-customer/{id}'
    public void deleteCustomer(int id){
        restTemplate.delete(BAST_URL+"delete-customer/{id}",id);
    }

    public List<Customer> getCustomers(){
        this.customers = listAllCustomers();
        return this.customers;
    }

    private List<Customer> customers;
    public CustomerService(){
        customers = new ArrayList<>();
        this.customers = listAllCustomers();
    }
    //localhost:8080/create-customer
    public Customer createCustomer(Customer customer){
        ResponseEntity<Customer> responseEntity=restTemplate
                .postForEntity(BAST_URL+"create-customer",customer
                        ,Customer.class);
        System.out.println("Status:"+ responseEntity.getStatusCode());
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            return responseEntity.getBody();
        }
        else
            throw new IllegalStateException("Customer cannot created.");
    }



    private List<Customer> listAllCustomers(){
        ResponseEntity<Customers>  customersResponseEntity=restTemplate
                .getForEntity(BAST_URL+"list-customers-v2?type=json",
                        Customers.class);
        if(customersResponseEntity.getStatusCode().is2xxSuccessful()){
            return customersResponseEntity.getBody().getCustomers();
        }
        else
            throw new IllegalStateException("Retrieve list customers error-" +
                    "status code : %s".formatted(customersResponseEntity.getStatusCode()));
    }
}
