package com.lms.gj_jewelry.interfaces;

import com.lms.gj_jewelry.application.UserService;
import com.lms.gj_jewelry.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value="/user", produces = "application/json;charset=utf-8")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
