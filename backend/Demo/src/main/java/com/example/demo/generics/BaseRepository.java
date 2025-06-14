package com.example.demo.generics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.*;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long>{
}