package com.example.demo.services;


import com.example.demo.entities.Customer;
import com.example.demo.entities.CustomerAndDebt;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.PaymentRepository;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.spi.ServiceRegistry;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    @PersistenceContext
    EntityManager entityManager;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public  Set<CustomerAndDebt> allDebts(Set<Customer> customers ){
        Set<CustomerAndDebt> customerAndDebt = new HashSet<>();
        Session session = entityManager.unwrap(Session.class);
        for(Customer x : customers) {
            CustomerAndDebt customerAndDebt1 = new CustomerAndDebt();
            customerAndDebt1.setCustomer(x);
            int debt = session.doReturningWork(connection -> {
                try (CallableStatement function = connection.prepareCall(
                        "{ ? = call debt(?)}")) {
                    function.registerOutParameter(1, Types.INTEGER);
                    function.setInt(2, x.getIdCustomer().intValue());
                    function.execute();
                    return function.getInt(1);
                }
            });
            customerAndDebt1.setDebt(debt);
            customerAndDebt.add(customerAndDebt1);
        }
        return customerAndDebt;
    }

    @Override
    public Set<Customer> getCustomer(){
        Set<Customer> customers = new HashSet<>();
        customerRepository.findAll().iterator().forEachRemaining(customers::add);
        return customers;
    }

    @Override
    public void deleteById(Long idToDelete){
        customerRepository.deleteById(idToDelete);
    }

    @Override
    public Customer findById(Long l){
        Optional<Customer> customer = customerRepository.findByIdCustomer(l);
        if(!customer.isPresent()){
            throw new RuntimeException("Customer not found!");
        }
        return customer.get();
    }


    @Override
    @Transactional
    public Customer saveCustomer(Customer customerCommand){
        Customer savedCustomer = customerRepository.save(customerCommand);
        return customerCommand;
    }
}
