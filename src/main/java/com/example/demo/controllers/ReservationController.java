package com.example.demo.controllers;

import com.example.demo.entities.Reservation;
import com.example.demo.services.CustomerService;
import com.example.demo.services.ReservationService;
import com.example.demo.services.RoomService;
import com.example.demo.services.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ReservationController {

    private final CustomerService customerService;
    private final WorkerService workerService;
    private final ReservationService reservationService;
    private final RoomService roomService;

    public ReservationController(CustomerService customerService, WorkerService workerService, ReservationService reservationService, RoomService roomService) {
        this.customerService = customerService;
        this.workerService = workerService;
        this.reservationService = reservationService;
        this.roomService = roomService;
    }

    @RequestMapping("/reservation/new")
    public String newReservation(Model model){
        model.addAttribute("reservation",new Reservation());
        model.addAttribute("rooms",roomService.getRoom());
        model.addAttribute("workers",workerService.getWorkers());
        model.addAttribute("customers",customerService.getCustomer());
        return "newReservation";
    }
    @PostMapping("/reservation")
    public  String saveOrUpdate(@Valid @ModelAttribute("reservation") Reservation command, BindingResult bindingResult,Model model){

        if(bindingResult.hasErrors()){
            command.setRoom(null);
            command.setWorker(null);
            command.setCustomer(null);
            model.addAttribute("reservation",command);
            model.addAttribute("rooms",roomService.getRoom());
            model.addAttribute("workers",workerService.getWorkers());
            model.addAttribute("customers",customerService.getCustomer());
            return "newReservation";
        }
        Reservation reservation = reservationService.saveReservation(command);
        return "redirect:/reservations";
    }

    @RequestMapping("/reservation/{id}/update")
    public String updateReservation(@PathVariable String id, Model model){
        Reservation reservation = reservationService.setToUpdate(reservationService.findById(Long.valueOf(id)));
        model.addAttribute("reservation",reservation);
        model.addAttribute("rooms",roomService.getRoom());
        model.addAttribute("workers",workerService.getWorkers());
        model.addAttribute("customers",customerService.getCustomer());
        return "newReservation";
    }

    @GetMapping
    @RequestMapping("/reservation/{id}/delete")
    public String deleteById(@PathVariable String id){
        reservationService.deleteById(Long.valueOf(id));
        return "redirect:/reservations";
    }
}
