package com.example.contestbackend.service;

import com.example.contestbackend.dto.DictionaryResponse;
import com.example.contestbackend.dto.TaskDto;
import com.example.contestbackend.dto.projections.TaskTableView;
import com.example.contestbackend.model.Task;
import com.example.contestbackend.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final SchoolClassService schoolClassService;

    public List<TaskTableView> getScores(){
        return taskRepository.findBy(TaskTableView.class);
    }

    public Task getScore(Integer id) {
        return taskRepository.findById(id).orElseThrow();
    }

    public Task createScore(TaskDto taskDto) {
        Task task = new Task(
                taskDto.getName(),
                taskDto.getScore(),
                schoolClassService.getSchoolClass(taskDto.getSchoolClass())
        );

        return taskRepository.save(task);
    }
    public void deleteScore(Integer id) {
        taskRepository.deleteById(id);
    }
    public void deleteScoreBySchoolClassId(Integer id) {
        taskRepository.deleteScoreBySchoolClassId(id);
    }


    public List<DictionaryResponse> findAllBySchoolClassId(Integer schoolClassId) {
        return taskRepository.findAllBySchoolClassId(schoolClassId);
    }

    public List<TaskTableView> getScoresByVoivodeshipId(String City) {
        return taskRepository.findAllBySchoolClassSchoolCity(City);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }
}
