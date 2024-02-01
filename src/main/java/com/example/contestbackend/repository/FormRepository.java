package com.example.contestbackend.repository;

import com.example.contestbackend.dto.projections.SchoolClassTableView;
import com.example.contestbackend.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<Form, Integer> {
    <T> Optional<T> findById(int id, Class<T> type);

//    List<SchoolClassTableView> findSchoolClass(Boolean isAccepted);
}
