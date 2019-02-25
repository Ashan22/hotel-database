package com.example.demo.controllers;

import com.example.demo.entities.Payment;
import com.example.demo.services.PaymentService;
import com.example.demo.services.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PaymentController {

    private final ReservationService reservationService;
    private final PaymentService paymentService;

    public PaymentController(ReservationService reservationService, PaymentService paymentService) {
        this.reservationService = reservationService;
        this.paymentService = paymentService;
    }

    @RequestMapping("/payment/new")
    public String newWorker(Model model){
        model.addAttribute("payment",new Payment());
        model.addAttribute("reservations",reservationService.getReservation());
        return "newPayment";
    }
    @PostMapping("/payment")
    public  String saveOrUpdate(@Valid @ModelAttribute("payment") Payment command, BindingResult bindingResult,Model model){

        if(bindingResult.hasErrors()){
            command.setReservation(null);
            model.addAttribute("payment",command);
            model.addAttribute("reservations",reservationService.getReservation());
            return "newPayment";
        }

        Payment payment = paymentService.savePayment(command);
        return "redirect:/payments";
    }

    @RequestMapping("/payment/{id}/update")
    public String updatePayment(@PathVariable String id, Model model){
        Payment payment = paymentService.setToUpdate(paymentService.findById(Long.valueOf(id)));
        model.addAttribute("payment",payment);
        model.addAttribute("reservations",reservationService.getReservation());
        return "newPayment";
    }

    @GetMapping
    @RequestMapping("/payment/{id}/delete")
    public String deleteById(@PathVariable String id){
        paymentService.deleteById(Long.valueOf(id));
        return "redirect:/payments";
    }
}
