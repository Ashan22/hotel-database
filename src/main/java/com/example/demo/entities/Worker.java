package com.example.demo.entities;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idWorker;

    @NotBlank
    @NotNull
    @Size(min= 2, max =40)
    private String name;

    @NotBlank
    @NotNull
    @Size(min=2,max = 40)
    private String surname;

    @NotNull
    @Min(1)
    private int salary;

    @OneToMany(mappedBy = "worker")
    private List<Reservation> reservations;


}
