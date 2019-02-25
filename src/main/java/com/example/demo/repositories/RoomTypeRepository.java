package com.example.demo.repositories;

import com.example.demo.entities.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomTypeRepository extends JpaRepository<RoomType,Long> {

    Optional <RoomType> findByIdRoomType(Long id);
}
