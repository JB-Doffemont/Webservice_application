package com.example.fisherfans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.fisherfans.dto.UserDto;
import com.example.fisherfans.entity.User;
import com.example.fisherfans.service.Impl.UserServiceImpl;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @PostMapping("")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @PatchMapping("/{id}")
    public User patchUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}
