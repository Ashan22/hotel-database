package com.example.demo.services;

import com.example.demo.entities.Payment;

import java.util.Set;

public interface PaymentService {

    Set<Payment> getPayment();
    Payment savePayment(Payment paymentCommand);
    void deleteById(Long idToDelete);
    Payment findById(Long l);
    Payment setToUpdate(Payment payment);
}
