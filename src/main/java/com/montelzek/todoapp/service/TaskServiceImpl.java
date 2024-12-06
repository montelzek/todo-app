package com.montelzek.todoapp.service;

import com.montelzek.todoapp.entity.Task;
import com.montelzek.todoapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository theTaskRepository) {
        taskRepository = theTaskRepository;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(int theId) {
        Optional<Task> result = taskRepository.findById(theId);

        Task theTask = null;

        if (result.isPresent()) {
            theTask = result.get();
        }
        else {
            throw new RuntimeException("Did not find task id - " + theId);
        }

        return theTask;
    }

    @Override
    public Task save(Task theTask) {
        return taskRepository.save(theTask);
    }

    @Override
    public void deleteById(int theId) {
        taskRepository.deleteById(theId);
    }

    public long getTaskCount() {
        return taskRepository.count();
    }
}
