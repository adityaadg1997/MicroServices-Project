package com.codewithaditya.HotelService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "micro_hotel")
public class Hotel {

    @Id
    private String id;
    private String name;
    private String location;
    private String about;
}
