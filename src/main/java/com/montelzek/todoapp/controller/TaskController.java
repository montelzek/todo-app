package com.montelzek.todoapp.controller;

import com.montelzek.todoapp.entity.Priority;
import com.montelzek.todoapp.entity.Task;
import com.montelzek.todoapp.service.PriorityService;
import com.montelzek.todoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final PriorityService priorityService;

    @Autowired
    public TaskController(TaskService theTaskService, PriorityService priorityService) {
        taskService = theTaskService;
        this.priorityService = priorityService;
    }


    @GetMapping("/list")
    public String listTasks(Model theModel) {

        List<Task> theTasks = taskService.findAll();

        theModel.addAttribute("tasks", theTasks);

        return "tasks/list-tasks";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        Task theTask = new Task();
        List<Priority> priorities = priorityService.findAll();

        theModel.addAttribute("task", theTask);
        theModel.addAttribute("priorities", priorities);

        return "tasks/task-form";
    }
}
