package com.example.contestbackend.repository;

import com.example.contestbackend.model.UtilsData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilsDataRepository extends JpaRepository<UtilsData, Integer> {

}
