package com.pantkiewicz.demo.picturemanager.picture.model;

import com.pantkiewicz.demo.picturemanager.event.model.Event;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PICTURES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Picture {

    public Picture(Event event, String name, String filePath) {
        this.event = event;
        this.name = name;
        this.filePath = filePath;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "EVENT_ID")
    public Event event;

    @Column(name = "NAME")
    private String name;

    @Column(name = "FILE_PATH")
    private String filePath;
}
