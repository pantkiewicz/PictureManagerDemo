package com.pantkiewicz.demo.picturemanager.user.repository;

import com.pantkiewicz.demo.picturemanager.user.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE upper(u.login) = upper(:login)")
    List<User> getByLogin(@Param("login") String login);
}
