package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Users;

public interface UserRespo extends JpaRepository<Users, Long>{

}
