package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCustomer;

    @NotBlank
    @NotNull
    @Size(min = 2,max = 40)
    private String name;

    @NotBlank
    @NotNull
    @Size(min =2 ,max = 40)
    private String surname;

    @OneToMany(mappedBy = "customer")
    private List<Reservation> reservations;

}
