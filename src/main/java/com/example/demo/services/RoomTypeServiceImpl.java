package com.example.demo.services;

import com.example.demo.entities.RoomType;
import com.example.demo.repositories.RoomTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RoomTypeServiceImpl implements RoomTypeService{

    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeServiceImpl(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public Set<RoomType> getRoomTypes() {
        Set<RoomType> roomTypesSet = new HashSet<>();
        roomTypeRepository.findAll().iterator().forEachRemaining(roomTypesSet::add);
        return roomTypesSet;
    }

    @Override
    @Transactional
    public RoomType saveRoomTypeCommand(RoomType roomTypeCommand){
        RoomType savedRoomType = roomTypeRepository.save(roomTypeCommand);
        return savedRoomType;
        }

    @Override
    public RoomType findById(Long l){

        Optional<RoomType> roomTypeOptional = roomTypeRepository.findByIdRoomType(l);
        if(!roomTypeOptional.isPresent()){
            throw new RuntimeException("Worker Not Found!");
        }
        return roomTypeOptional.get();
    }
    @Override
    public void deleteById(Long idToDelete){
        roomTypeRepository.deleteById(idToDelete);
    }
}
