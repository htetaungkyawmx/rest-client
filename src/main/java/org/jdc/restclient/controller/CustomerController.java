package org.jdc.restclient.controller;

import lombok.RequiredArgsConstructor;
import org.jdc.restclient.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/")
    public String index() {
        return "redirect:/list-customers";
    }

    @GetMapping("/list-customers")
    public String listAllCustomers(Model model) {
        model.addAttribute("customers"
                ,customerService.getCustomers());
        return "customers";
    }
}
