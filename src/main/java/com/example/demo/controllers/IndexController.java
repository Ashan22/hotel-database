package com.example.demo.controllers;


import com.example.demo.entities.Customer;
import com.example.demo.services.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.security.auth.login.Configuration;
import java.util.Set;

@Controller
public class IndexController {
    private final WorkerService workerService;
    private final CustomerService customerService;
    private final RoomTypeService roomTypeService;
    private final RoomService roomService;
    private final ReservationService reservationService;
    private final PaymentService paymentService;

    public IndexController(WorkerService workerService, CustomerService customerService, RoomTypeService roomTypeService, RoomService roomService, ReservationService reservationService, PaymentService paymentService) {
        this.workerService = workerService;
        this.customerService = customerService;
        this.roomTypeService = roomTypeService;
        this.roomService = roomService;
        this.reservationService = reservationService;
        this.paymentService = paymentService;
    }

    @RequestMapping({"/","","/index"})
    public String getIndexPage(){
        return "index";
    }

    @RequestMapping({"/workers"})
    public String getWorkerPage(Model model){
    model.addAttribute("workers",workerService.getWorkers());
    return "workers";
    }

    @RequestMapping({"/customers"})
    public String getCustomerPage(Model model){
        model.addAttribute("customers",customerService.allDebts(customerService.getCustomer()));
        return "customers";
    }

    @RequestMapping({"/rooms"})
    public String getRoomPage(Model model){
        model.addAttribute("rooms",roomService.getRoom());
        return "rooms";
    }

    @RequestMapping({"/roomtypes"})
    public String getRoomTypePage(Model model){
        model.addAttribute("roomtypes",roomTypeService.getRoomTypes());
        return "roomtypes";
    }

    @RequestMapping({"/reservations"})
    public String getReservationPage(Model model){
        model.addAttribute("reservations",reservationService.getReservation());
        return "reservations";
    }

    @RequestMapping({"/payments"})
    public String getPaymentPage(Model model){
        model.addAttribute("payments",paymentService.getPayment());
        return "payments";
    }
}