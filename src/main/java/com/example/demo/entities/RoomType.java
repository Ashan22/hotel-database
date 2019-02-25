package com.example.demo.entities;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@NamedStoredProcedureQuery(
        name="changeCost",
        procedureName = "sales",
        resultClasses = { RoomType.class },
        parameters = {
                @StoredProcedureParameter(name = "percent",type = Integer.class,mode = ParameterMode.IN)
        }
)

@Entity
@Data
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idRoomType;

    @NotNull
    @Min(1)
    private int maxPeople;

    @NotNull
    @Min(0)
    private int cost;

    @NotNull
    @Min(1)
    private int size;

    @OneToMany(mappedBy = "roomType")
    private List<Room> rooms;

}
