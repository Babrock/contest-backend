package com.example.contestbackend.repository;

import com.example.contestbackend.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    Region findByName(String voivodeshipName);
}
