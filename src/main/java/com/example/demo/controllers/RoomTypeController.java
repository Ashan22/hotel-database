package com.example.demo.controllers;

import com.example.demo.entities.Procedure;
import com.example.demo.entities.RoomType;
import com.example.demo.services.RoomTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.validation.Valid;

@Controller
public class RoomTypeController {

    private final RoomTypeService roomTypeService;

    @PersistenceContext
    EntityManager entityManager;

    public RoomTypeController(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @RequestMapping("/roomtype/new")
    public String newRoomType(Model model){
        model.addAttribute("roomtype",new RoomType());
        return "newRoomType";
    }
    @PostMapping("roomtype")
    public  String saveOrUpdate(@Valid @ModelAttribute("roomtype") RoomType command, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "newRoomType";
        }

        RoomType roomTypeCommand = roomTypeService.saveRoomTypeCommand(command);
        return "redirect:/roomtypes";
    }

    @RequestMapping("/roomtype/{id}/update")
    public String updateRoomType(@PathVariable String id, Model model){
        model.addAttribute("roomtype",roomTypeService.findById(Long.valueOf(id)));
        return "newRoomType";
    }

    @GetMapping
    @RequestMapping("/roomtype/{id}/delete")
    public String deleteById(@PathVariable String id){
        roomTypeService.deleteById(Long.valueOf(id));
        return "redirect:/roomtypes";
    }

    @RequestMapping("/sales")
    public String sales(Model model){
        model.addAttribute("procedure", new Procedure());
        return "sales";
    }
    @PostMapping("sales")
    public String modifyAllCosts(@Valid @ModelAttribute("procedure") Procedure procedure, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "sales";
        }
       else {
            try {
                StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sales")
                        .registerStoredProcedureParameter(1,Integer.class,ParameterMode.IN);
                storedProcedureQuery.setParameter(1,procedure.getPercent());
                storedProcedureQuery.execute();
            }
            catch (Exception e){
                e.printStackTrace();
                return "redirect:/roomtypes";
            }
            return "redirect:/roomtypes";
        }
    }

}
