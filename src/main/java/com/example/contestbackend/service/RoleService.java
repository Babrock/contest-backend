package com.example.contestbackend.service;

import com.example.contestbackend.model.Role;
import com.example.contestbackend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;

    public List<Role> getRoles() {
        return repository.findAll();
    }
    public Role getRole(int id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Role with given id not found"));
    }


}
