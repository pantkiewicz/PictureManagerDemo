package com.pantkiewicz.demo.picturemanager.config;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.pantkiewicz.demo.picturemanager.PictureManagerDemoApplication;
import com.pantkiewicz.demo.picturemanager.event.model.Event;
import com.pantkiewicz.demo.picturemanager.order.model.Order;
import com.pantkiewicz.demo.picturemanager.picture.model.Picture;
import com.pantkiewicz.demo.picturemanager.user.model.User;
import com.pantkiewicz.demo.picturemanager.event.repository.EventRepository;
import com.pantkiewicz.demo.picturemanager.order.repository.OrderRepository;
import com.pantkiewicz.demo.picturemanager.picture.repository.PictureRepository;
import com.pantkiewicz.demo.picturemanager.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class StartupDataLoader implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(PictureManagerDemoApplication.class);

    @Override
    public void run(ApplicationArguments args) {
        logger.info("Database initialization started");
        initDatabase();
        logger.info("Database initialization completed!");

    }

    private void initDatabase() {
        List<User> users = Lists.newArrayList();
        users.add(new User("patryk", passwordEncoder.encode("qwerty123")));
        users.add(new User("john", passwordEncoder.encode("abc123")));
        users = (List<User>) userRepository.saveAll(users);

        List<Event> events = Lists.newArrayList();
        events.add(new Event(LocalDate.of(2019, 4, 28), "Culture festival", ""));
        events.add(new Event(LocalDate.of(2019, 5, 5), "Children's day", ""));
        events = (List<Event>) eventRepository.saveAll(events);

        List<Picture> pictures = Lists.newArrayList();
        pictures.add(new Picture(events.get(0), "PIC_001", "/test/PIC_001.png"));
        pictures.add(new Picture(events.get(0), "PIC_002", "/test/PIC_002.png"));
        pictures.add(new Picture(events.get(0), "PIC_003", "/test/PIC_003.png"));

        pictures.add(new Picture(events.get(1), "PIC_004", "/test/PIC_004.png"));
        pictures.add(new Picture(events.get(1), "PIC_005", "/test/PIC_005.png"));
        pictures = (List<Picture>) pictureRepository.saveAll(pictures);

        List<Order> orders = Lists.newArrayList();
        orders.add(new Order(users.get(0), ImmutableList.of(pictures.get(0), pictures.get(2))));
        orders.add(new Order(users.get(1), ImmutableList.of(pictures.get(1), pictures.get(2))));
        orders.add(new Order(users.get(0), ImmutableList.of(pictures.get(3))));
        orders.add(new Order(users.get(1), ImmutableList.of(pictures.get(3), pictures.get(4))));
        orderRepository.saveAll(orders);
    }
}
