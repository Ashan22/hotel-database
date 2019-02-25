package com.example.demo.services;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Reservation;
import com.example.demo.entities.Room;
import com.example.demo.entities.Worker;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.ReservationRepository;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.repositories.WorkerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;
    private final WorkerRepository workerRepository;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, WorkerRepository workerRepository, CustomerRepository customerRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.workerRepository = workerRepository;
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public Set<Reservation> getReservation() {
        Set<Reservation> reservationSet = new HashSet<>();
        reservationRepository.findAll().iterator().forEachRemaining(reservationSet::add);
        return reservationSet;
    }

    @Override
    @Transactional
    public Reservation saveReservation(Reservation reservationCommand){
        Room room = roomRepository.findById(reservationCommand.getRoom().getIdRoom()).get();
        Worker worker = workerRepository.findById(reservationCommand.getWorker().getIdWorker()).get();
        Customer customer = customerRepository.findById(reservationCommand.getCustomer().getIdCustomer()).get();
        reservationCommand.setWorker(worker);
        reservationCommand.setCustomer(customer);
        reservationCommand.setRoom(room);
        Reservation savedReservation = reservationRepository.save(reservationCommand);
        worker.getReservations().add(savedReservation);
        customer.getReservations().add(savedReservation);
        room.getReservations().add(savedReservation);
        return savedReservation;
    }

    @Override
    public Reservation findById(Long l){

        Optional<Reservation> reservationOptional = reservationRepository.findByIdReservation(l);
        if(!reservationOptional.isPresent()){
            throw new RuntimeException("Reservation Not Found!");
        }
        return reservationOptional.get();
    }
    @Override
    public void deleteById(Long idToDelete){
        reservationRepository.deleteById(idToDelete);
    }

    @Override
    public Reservation setToUpdate(Reservation reservation){
        Reservation reservation1 = new Reservation();
        reservation1.setStartDate(reservation.getStartDate());
        reservation1.setDays(reservation.getDays());
        reservation1.setIdReservation(reservation.getIdReservation());
        return reservation1;
    }
}
