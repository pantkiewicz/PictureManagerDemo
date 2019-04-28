package com.pantkiewicz.demo.picturemanager.user.service;

import com.pantkiewicz.demo.picturemanager.user.model.User;
import com.pantkiewicz.demo.picturemanager.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User saveUser(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public LoginResult tryLogIn(String login, String password) {
        Optional<User> user = userRepository.getByLogin(login).stream().findFirst();
        if(!user.isPresent()) {
            return LoginResult.notFound();
        }

        if(!isPasswordCorrect(user.get(), password)) {
            return LoginResult.incorrectPassword();
        }

        return LoginResult.success(user.get().getId());
    }

    private boolean isPasswordCorrect(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }
}
