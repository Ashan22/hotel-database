package com.example.demo.entities;


import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idRoom;

    @NotNull
    private String availability;

    @NotNull
    @Min(1)
    private int roomNumber;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;

    @NotNull
    @Min(0)
    private int floor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id", foreignKey = @ForeignKey(name = "fk_room_type_id"))
    private RoomType roomType;

}
