package com.example.demo.controllers;

import com.example.demo.entities.Worker;
import com.example.demo.services.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @RequestMapping("/worker/new")
    public String newWorker(Model model){
        model.addAttribute("worker",new Worker());
        return "newWorker";
    }
    @PostMapping("worker")
    public  String saveOrUpdate(@Valid @ModelAttribute("worker") Worker command, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "newWorker";
        }

        Worker workerCommand = workerService.saveWorkerCommand(command);
        return "redirect:/workers";
    }

    @RequestMapping("/worker/{id}/update")
    public String updateWorker(@PathVariable String id,Model model){
        model.addAttribute("worker",workerService.findById(Long.valueOf(id)));
        return "newWorker";
    }

    @GetMapping
    @RequestMapping("/worker/{id}/delete")
    public String deleteById(@PathVariable String id){
        workerService.deleteById(Long.valueOf(id));
        return "redirect:/workers";
    }
}
