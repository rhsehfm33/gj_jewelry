package com.lms.gj_jewelry.interfaces;

import com.lms.gj_jewelry.application.UserService;
import com.lms.gj_jewelry.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value="/user", produces = "application/json;charset=utf-8")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(
            @RequestBody @Valid User user
    ) {
        return userService.createUser(user);
    }

    @GetMapping("/all")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/account/{account}")
    public User getUserByAccount(@PathVariable String account) {
        return userService.getUserByAccount(account);
    }

    @GetMapping("/phoneNumber/{phoneNumber}")
    public User getUserByPhoneNumber(@PathVariable String phoneNumber) {
        return userService.getUserByPhoneNumber(phoneNumber);
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PatchMapping("")
    public User updateUser(
            @RequestBody @Valid User user
    ) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/id/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
