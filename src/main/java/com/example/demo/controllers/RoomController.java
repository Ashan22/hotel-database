package com.example.demo.controllers;

import com.example.demo.entities.Room;
import com.example.demo.entities.RoomType;
import com.example.demo.services.RoomService;
import com.example.demo.services.RoomTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Controller
public class RoomController {

    private final RoomService roomService;
    private final RoomTypeService roomTypeService;

    public RoomController(RoomService roomService, RoomTypeService roomTypeService) {
        this.roomService = roomService;
        this.roomTypeService = roomTypeService;
    }

    @RequestMapping("/room/new")
    public String newRoom(Model model){
        Set<RoomType> roomTypeSet = roomTypeService.getRoomTypes();
        model.addAttribute("room",new Room());
        model.addAttribute("types",roomTypeSet);
        model.addAttribute("default",null);
        return "newRoom";
    }
    @PostMapping("/room")
    public  String saveOrUpdate(@Valid Room command, BindingResult bindingResult,Model model){

        if(bindingResult.hasErrors()){

            model.addAttribute("default",roomTypeService.findById(Long.valueOf(command.getRoomType().getIdRoomType())));
            roomService.setToUpdate(command);
            model.addAttribute("types",roomTypeService.getRoomTypes());
            model.addAttribute("room",command);
            return "newRoom";
        }
        Room roomCommand = roomService.saveRoom(command);
        return "redirect:/rooms";
    }

    @RequestMapping("/room/{id}/update")
    public String updateRoom(@PathVariable String id, Model model){
        Room room = roomService.findById(Long.valueOf(id));
        model.addAttribute("default",roomTypeService.findById(Long.valueOf(room.getRoomType().getIdRoomType())));
        roomService.setToUpdate(room);
        model.addAttribute("types",roomTypeService.getRoomTypes());
        model.addAttribute("room",room);
        return "newRoom";
    }

    @GetMapping
    @RequestMapping("/room/{id}/delete")
    public String deleteById(@PathVariable String id){
        roomService.deleteById(Long.valueOf(id));
        return "redirect:/rooms";
    }
}
