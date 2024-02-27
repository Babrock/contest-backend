package com.example.contestbackend.controller;

import com.example.contestbackend.model.Role;
import com.example.contestbackend.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public List<Role> getRoles(){return roleService.getRoles();}
}
