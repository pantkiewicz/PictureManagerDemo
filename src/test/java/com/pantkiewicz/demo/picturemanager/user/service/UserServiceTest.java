package com.pantkiewicz.demo.picturemanager.user.service;

import com.pantkiewicz.demo.picturemanager.user.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    private User user;

    @Before
    public void initUser() {
        user = userService.saveUser("testuser", "pass123");
    }

    @After
    public void deleteUser() {
        userService.deleteUser(user);
    }

    @Test
    public void whenLoginPasswordCorrect_thenOK() {
        LoginResult result = userService.tryLogIn("testuser", "pass123");
        Assert.assertTrue(LoginStatus.SUCCESS.equals(result.getStatus()));
        Assert.assertTrue(result.getUserId().isPresent());
    }

    @Test
    public void whenLoginUpperCase_thenOK() {
        LoginResult result = userService.tryLogIn("TestUSER", "pass123");
        Assert.assertTrue(LoginStatus.SUCCESS.equals(result.getStatus()));
        Assert.assertTrue(result.getUserId().isPresent());
    }

    @Test
    public void whenLoginIncorrect_thenFail() {
        LoginResult result = userService.tryLogIn("unknown_user", "pass123");
        Assert.assertEquals(result.getStatus(), LoginStatus.USER_NOT_FOUND);
        Assert.assertFalse(result.getUserId().isPresent());
    }

    @Test
    public void whenPasswordIncorrect_thenFail() {
        LoginResult result = userService.tryLogIn("testuser", "wrongPassword");
        Assert.assertEquals(result.getStatus(), LoginStatus.INCORRECT_PASSWORD);
        Assert.assertFalse(result.getUserId().isPresent());
    }
}
