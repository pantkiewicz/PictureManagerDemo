package com.pantkiewicz.demo.picturemanager.order.model;

import com.pantkiewicz.demo.picturemanager.picture.model.Picture;
import com.pantkiewicz.demo.picturemanager.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    public Order(User user, List<Picture> pictures) {
        this.user = user;
        this.pictures = pictures;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @ManyToMany
    @JoinTable(name="ORDER_PICTURES", joinColumns = @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PICTURE_ID"))
    private List<Picture> pictures;
}
