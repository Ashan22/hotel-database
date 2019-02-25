package com.example.demo.services;

import com.example.demo.entities.Customer;
import com.example.demo.entities.CustomerAndDebt;
import org.hibernate.Session;

import java.util.List;
import java.util.Set;

public interface CustomerService  {

    Set<Customer> getCustomer();
    Customer saveCustomer(Customer customerCommand);
    void deleteById(Long idToDelete);
    Customer findById(Long l);
    Set<CustomerAndDebt> allDebts(Set<Customer> customers );

}
