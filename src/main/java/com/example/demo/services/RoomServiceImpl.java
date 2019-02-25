package com.example.demo.services;

import com.example.demo.entities.Room;
import com.example.demo.entities.RoomType;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.repositories.RoomTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;

    public RoomServiceImpl(RoomRepository roomRepository, RoomTypeRepository roomTypeRepository) {
        this.roomRepository = roomRepository;
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public Set<Room> getRoom() {
        Set<Room> rooms = new HashSet<>();
        roomRepository.findAll().iterator().forEachRemaining(rooms::add);
        return rooms;
    }

    @Override
    @Transactional
    public Room saveRoom(Room roomCommand){
        RoomType roomType = roomTypeRepository.findById(roomCommand.getRoomType().getIdRoomType()).get();
        Room savedRoom = roomRepository.save(roomCommand);
        roomType.getRooms().add(savedRoom);
        return savedRoom;
    }

    @Override
    public Room findById(Long l){

        Optional<Room> roomOptional = roomRepository.findByIdRoom(l);
        if(!roomOptional.isPresent()){
            throw new RuntimeException("Room Not Found!");
        }
        return roomOptional.get();
    }
    @Override
    public void deleteById(Long idToDelete){
        roomRepository.deleteById(idToDelete);
    }

    @Override
    public Room setToUpdate(Room room){
        room.setReservations(null);
        room.setRoomType(null);
        return room;
    }
}
