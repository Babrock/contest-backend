package com.example.contestbackend.repository;

import com.example.contestbackend.model.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer> {
}
