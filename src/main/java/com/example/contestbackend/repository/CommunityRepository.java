package com.example.contestbackend.repository;

import com.example.contestbackend.dto.DictionaryResponse;
import com.example.contestbackend.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer> {
    Community findById(int id);
    List<DictionaryResponse> findAllByCountyId(Integer id);

    Community findByName(String communityName);

    List<Community> findAllByName(String communityName);
}
