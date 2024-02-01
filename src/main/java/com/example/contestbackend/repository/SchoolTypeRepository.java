package com.example.contestbackend.repository;

import com.example.contestbackend.model.SchoolType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolTypeRepository extends JpaRepository<SchoolType, Integer> {
}
