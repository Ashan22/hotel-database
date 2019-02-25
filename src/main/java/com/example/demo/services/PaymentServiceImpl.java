package com.example.demo.services;

import com.example.demo.entities.Payment;
import com.example.demo.entities.Reservation;
import com.example.demo.repositories.PaymentRepository;
import com.example.demo.repositories.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, ReservationRepository reservationRepository) {
        this.paymentRepository = paymentRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Set<Payment> getPayment() {
        Set<Payment> paymentSet = new HashSet<>();
        paymentRepository.findAll().iterator().forEachRemaining(paymentSet::add);
        return paymentSet;
    }

    @Override
    @Transactional
    public Payment savePayment(Payment paymentCommand){
        Reservation reservation = reservationRepository.findById(paymentCommand.getReservation().getIdReservation()).get();
        paymentCommand.setReservation(reservation);
        Payment savedPayment = paymentRepository.save(paymentCommand);
        return savedPayment;
    }

    @Override
    public Payment findById(Long l){

        Optional<Payment> paymentOptional = paymentRepository.findByPaymentId(l);
        if(!paymentOptional.isPresent()){
            throw new RuntimeException("Payment Not Found!");
        }
        return paymentOptional.get();
    }
    @Override
    public void deleteById(Long idToDelete){
        paymentRepository.deleteById(idToDelete);
    }

    @Override
    public Payment setToUpdate(Payment object){
        object.setReservation(null);
        return object;
    }
}
