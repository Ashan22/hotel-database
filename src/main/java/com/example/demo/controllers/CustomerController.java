package com.example.demo.controllers;

import com.example.demo.entities.Customer;
import com.example.demo.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@Controller
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/customer/new")
    public String newCustomer(Model model){
        model.addAttribute("customer",new Customer());
        return "newCustomer";
    }
    @PostMapping("customer")
    public  String saveOrUpdate(@Valid @ModelAttribute("customer") Customer command, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return "newCustomer";
        }

        Customer customerCommand = customerService.saveCustomer(command);
        return "redirect:/customers";
    }

    @RequestMapping("/customer/{id}/update")
    public String updateCustomer(@PathVariable String id, Model model){
        model.addAttribute("customer",customerService.findById(Long.valueOf(id)));
        return "newCustomer";
    }

    @GetMapping
    @RequestMapping("/customer/{id}/delete")
    public String deleteById(@PathVariable String id){
        customerService.deleteById(Long.valueOf(id));
        return "redirect:/customers";
    }
}
