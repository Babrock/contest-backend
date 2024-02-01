package com.example.contestbackend.controller;

import com.example.contestbackend.model.Category;
import com.example.contestbackend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Integer id) { return categoryService.getCategory(id);}

    @GetMapping
    public List<Category> getAll() { return categoryService.getCategories(); }
}
