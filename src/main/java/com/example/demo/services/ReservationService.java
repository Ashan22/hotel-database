package com.example.demo.services;


import com.example.demo.entities.Reservation;

import java.util.Set;

public interface ReservationService {

    Set<Reservation> getReservation();
    Reservation saveReservation(Reservation reservationCommand);
    void deleteById(Long idToDelete);
    Reservation findById(Long l);
    Reservation setToUpdate(Reservation reservation);
}
