package com.montelzek.todoapp.controller;

import com.montelzek.todoapp.entity.Task;
import com.montelzek.todoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService theTaskService) {
        taskService = theTaskService;
    }

    @GetMapping("/list")
    public String listTasks(Model theModel) {

        List<Task> theTasks = taskService.findAll();

        theModel.addAttribute("tasks", theTasks);

        return "tasks/list-tasks";
    }
}
