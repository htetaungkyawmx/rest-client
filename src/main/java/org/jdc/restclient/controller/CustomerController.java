package org.jdc.restclient.controller;

import lombok.RequiredArgsConstructor;
import org.jdc.restclient.ds.Customer;
import org.jdc.restclient.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/update-customer/{id}")
    public String updateCustomer(@PathVariable int id, Model model){
        model.addAttribute("customer",
                customerService.findCustomerById(id));
        model.addAttribute("update",true);
        this.updateId = id;
        return "customerForm";
    }
    int updateId;
    @PostMapping("/update-customer")
    public String saveUpdateCustomer(Customer customer){
        System.out.println(customer.getFirstName() + " "+ customer.getLastName());
        customerService.saveUpdateCustomer(updateId,customer);
        return "redirect:/";
    }
    ///delete-customer/{id}
    @GetMapping("/delete-customer")
    public String deleteCustomer(@RequestParam int id){
        System.out.println("id:::::::::::::::::::::::::::::::"+ id);
        customerService.deleteCustomer(id);
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(){
        return "redirect:/list-customers";
    }
    @GetMapping("/list-customers")
    public String listAllCustomers(Model model){
        model.addAttribute("customers"
                ,customerService.getCustomers());
        return "customers";
    }
    @GetMapping("/create-customer")
    public String customerForm(Model model){
        model.addAttribute("update",false);
        model.addAttribute("customer",new Customer());
        return "customerForm";
    }
    @PostMapping("/create-customer")
    public String saveCustomer(Customer customer, BindingResult result){
        if(result.hasErrors()){
            return "customerForm";
        }
        customerService.createCustomer(customer);
        return "redirect:/";
    }
}
