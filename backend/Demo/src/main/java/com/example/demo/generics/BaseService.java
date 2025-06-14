package com.example.demo.generics;

import java.util.List;

public interface BaseService<E, ReqDTO, ResDTO> {
	List<ResDTO> findAll();
	ResDTO findById(Long id);
    ResDTO save(ReqDTO ReqDTO);
    ResDTO update(Long id, ReqDTO ReqDTO);
    void delete(Long id);
}