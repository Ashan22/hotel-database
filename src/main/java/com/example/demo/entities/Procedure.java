package com.example.demo.entities;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class Procedure {

    @Min(-100)
    private Integer percent;

}
