package com.example.demo.repositories;

import com.example.demo.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface WorkerRepository extends JpaRepository<Worker,Long> {

    Optional <Worker> findByIdWorker(Long id);
}
