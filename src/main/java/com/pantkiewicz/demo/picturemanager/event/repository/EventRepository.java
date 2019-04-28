package com.pantkiewicz.demo.picturemanager.event.repository;

import com.pantkiewicz.demo.picturemanager.event.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer> {
}
