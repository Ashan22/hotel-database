package com.example.demo.services;


import com.example.demo.entities.RoomType;

import java.util.Set;

public interface RoomTypeService {

    Set<RoomType> getRoomTypes();
    RoomType saveRoomTypeCommand(RoomType roomTypeCommand);
    void deleteById(Long idToDelete);
    RoomType findById(Long l);
}
