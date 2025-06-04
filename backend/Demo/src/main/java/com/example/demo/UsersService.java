package com.example.demo;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.demo.entity.*;
import com.example.demo.respository.UserRespo;

@Service
public class UsersService {
	@Autowired
    private UserRespo userRespository;

    public List<Users> getAllFlowers() {
        return userRespository.findAll();
    }

    public Users addFlower(Users user) {
        return userRespository.save(user);
    }
}
