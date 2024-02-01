package com.example.contestbackend.repository;

import com.example.contestbackend.dto.DictionaryResponse;
import com.example.contestbackend.dto.projections.TaskTableView;
import com.example.contestbackend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    void deleteScoreBySchoolClassId(Integer id);

    List<DictionaryResponse> findAllBySchoolClassId(Integer id);

    List<TaskTableView> findAllBySchoolClassSchoolCity(String city);

    <T> List<T> findBy(Class<T> type);
}
