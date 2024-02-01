package com.example.contestbackend.repository;

import com.example.contestbackend.dto.projections.SchoolClassTableView;
import com.example.contestbackend.dto.projections.SchoolClassVerifyView;
import com.example.contestbackend.model.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Integer> {
    List<SchoolClassVerifyView> findByFormId(Integer id);
    void deleteAllByFormId(Integer id);

    List<SchoolClassTableView> findByForm_IsAccepted(Boolean isAccepted);
}
