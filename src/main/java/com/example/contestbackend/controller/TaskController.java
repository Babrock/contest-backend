package com.example.contestbackend.controller;

import com.example.contestbackend.dto.DictionaryResponse;
import com.example.contestbackend.dto.TaskDto;
import com.example.contestbackend.dto.projections.TaskTableView;
import com.example.contestbackend.model.Task;
import com.example.contestbackend.service.SchoolClassService;
import com.example.contestbackend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final SchoolClassService schoolClassService;

    @GetMapping
    public List<TaskTableView> getScores(@RequestParam Optional<String> voivodeship){
        if(voivodeship.isPresent()) {
            return taskService.getScoresByVoivodeshipId(String.valueOf(voivodeship));
        }
        return taskService.getScores();
    }

    @GetMapping("/{id}")
    public Task getScore(@PathVariable Integer id){ return taskService.getScore(id);}

    @GetMapping(params = "schoolClass_id")
    public List<DictionaryResponse> getAllTasksBySchoolId(@RequestParam(name = "schoolClass_id") Integer schoolClassId){
        return taskService.findAllBySchoolClassId(schoolClassId);
    }

    @PostMapping("/add")
    public Task createScore(@RequestBody TaskDto taskDto) {
        return taskService.createScore(taskDto);}


    @DeleteMapping("/{id}")
    public void deleteScore(@PathVariable Integer id) {
        taskService.deleteScore(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Integer id, @RequestBody TaskDto taskDto){
        return taskService.updateTask(new Task(
                id,
                taskDto.getName(),
                taskDto.getScore(),
                schoolClassService.getSchoolClass(taskDto.getSchoolClass())
        ));
    }

}