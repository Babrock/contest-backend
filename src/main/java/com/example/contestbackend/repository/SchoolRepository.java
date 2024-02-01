package com.example.contestbackend.repository;

import com.example.contestbackend.dto.SchoolDetails;
import com.example.contestbackend.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, Integer> {

    <T>Optional<T> findByName(String name);
    <T>Optional<T> findById(int id, Class<T> type);

    <T> List <T> findAllByCity(String city, Class<T> type);

}
