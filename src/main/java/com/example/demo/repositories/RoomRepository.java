package com.example.demo.repositories;

import com.example.demo.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoomRepository extends JpaRepository<Room,Long> {

    Optional <Room> findByIdRoom(Long id);
}
