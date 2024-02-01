package com.example.contestbackend.repository;

import com.example.contestbackend.dto.DictionaryResponse;
import com.example.contestbackend.model.County;
import com.example.contestbackend.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountyRepository extends JpaRepository<County, Integer> {
    List<DictionaryResponse> findAllByVoivodeshipId(Integer id);

    List<DictionaryResponse> findAllByVoivodeshipIdIn(List<Integer> voivodeshipIds);

    County findByName(String countyName);

    boolean existsByName(String countyName);

    List<County> findAllByIdIn(List<Integer> id);

    List<County> findByRegionId(Integer id);

    List<County> findByRegion(Region region);

    List<County> findDetailsByRegion(Region region);



}
