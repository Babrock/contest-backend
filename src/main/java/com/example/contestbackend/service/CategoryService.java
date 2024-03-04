package com.example.contestbackend.service;

import com.example.contestbackend.model.Category;
import com.example.contestbackend.model.User;
import com.example.contestbackend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public Category getCategory(Integer id) {
        return categoryRepository.findById(id).orElseThrow();
    }
    public List<Category> getCategories(){ return categoryRepository.findAll(); }

    public Category getCategory(int id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Category with given id not found"));
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
}
