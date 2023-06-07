package com.example.day6.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String name;
    private String color;
    private String brand;
    private String type;
    private Date year;
    private Long price;
    private String description;

}
