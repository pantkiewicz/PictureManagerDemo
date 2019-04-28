package com.pantkiewicz.demo.picturemanager.order.repository;

import com.pantkiewicz.demo.picturemanager.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT e.id as eventId, e.name as eventName, p.id as photoId, p.name as photoName, count(*) as pictureCount " +
            "FROM Order o JOIN o.pictures p JOIN p.event e " +
            "WHERE e.date BETWEEN :startDate AND :endDate " +
            "GROUP BY e.id, e.name, p.id, p.name")
    List<OrderedPicturesCountDTO> countOrderedPicturesForPeriod(@Param("startDate") LocalDate startDate,
                                                                @Param("endDate") LocalDate endDate);
}
