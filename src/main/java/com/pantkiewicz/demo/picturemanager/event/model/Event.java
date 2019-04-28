package com.pantkiewicz.demo.picturemanager.event.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "EVENTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    public Event(LocalDate date, String name, String description) {
        this.date = date;
        this.name = name;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;
}
