package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.*;
import com.example.demo.entity.*;

@RestController
@RequestMapping("/users")
public class HelloController {
	@Autowired
    private UsersService userService;

    @GetMapping
    public List<Users> getAllFlowers() {
        return userService.getAllFlowers();
    }

    @PostMapping
    public Users addFlower(@RequestBody Users user) {
        return userService.addFlower(user);
    }
}