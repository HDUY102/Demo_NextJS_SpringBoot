package com.example.demo.generics;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

public abstract class BaseController<ReqDTO extends BaseDTO, ResDTO extends BaseDTO> {
    protected abstract BaseService<?, ReqDTO, ResDTO> getService();

    @GetMapping
    public ResponseEntity<List<ResDTO>> getAll() {
        return ResponseEntity.ok(getService().findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(getService().findById(id));
    }

    @PostMapping
    public ResponseEntity<ResDTO> create(@RequestBody @Valid ReqDTO dto) {
        return ResponseEntity.ok(getService().save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResDTO> update(@PathVariable Long id, @RequestBody @Valid ReqDTO dto) {
        return ResponseEntity.ok(getService().update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        getService().delete(id);
        return ResponseEntity.noContent().build();
    }
}