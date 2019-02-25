package com.example.demo.services;

import com.example.demo.entities.Worker;
import com.example.demo.repositories.WorkerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;

    public WorkerServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public Set<Worker> getWorkers() {
        Set<Worker> workerSet = new HashSet<>();
        workerRepository.findAll().iterator().forEachRemaining(workerSet::add);
        return workerSet;
    }

    @Override
    @Transactional
    public Worker saveWorkerCommand(Worker workerCommand){
        Worker savedWorker = workerRepository.save(workerCommand);
        return savedWorker;
    }

    @Override
    public Worker findById(Long l){

        Optional <Worker> workerOptional = workerRepository.findByIdWorker(l);
        if(!workerOptional.isPresent()){
            throw new RuntimeException("Worker Not Found!");
        }
        return workerOptional.get();
    }
    @Override
    public void deleteById(Long idToDelete){
        workerRepository.deleteById(idToDelete);
    }
}
