package com.example.demo.services;

import com.example.demo.entities.Worker;

import java.util.Set;

public interface WorkerService {

    Set<Worker> getWorkers();
    Worker saveWorkerCommand(Worker workerCommand);
    void deleteById(Long idToDelete);
    Worker findById(Long l);

}
