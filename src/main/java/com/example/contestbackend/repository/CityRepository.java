package com.example.contestbackend.repository;

import com.example.contestbackend.dto.DictionaryResponse;
import com.example.contestbackend.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    List<DictionaryResponse> findAllByCommunityId(int id);

    City findByName(String cityName);

    City findByCommunityId(Integer id);
}
