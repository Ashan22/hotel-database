package com.example.demo.services;

import com.example.demo.entities.Room;

import java.util.Set;

public interface RoomService {

    Set<Room> getRoom();
    Room saveRoom(Room roomCommand);
    void deleteById(Long idToDelete);
    Room findById(Long l);
    Room setToUpdate(Room room);
}
