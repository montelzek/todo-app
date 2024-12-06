package com.montelzek.todoapp.controller;

import com.montelzek.todoapp.entity.Priority;
import com.montelzek.todoapp.entity.Task;
import com.montelzek.todoapp.service.PriorityService;
import com.montelzek.todoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        long taskCounter = taskService.getTaskCount();

        theModel.addAttribute("tasks", theTasks);
        theModel.addAttribute("taskCounter", taskCounter);

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

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("taskId") int theId, Model theModel) {

        Task theTask = taskService.findById(theId);
        List<Priority> priorities = priorityService.findAll();

        theModel.addAttribute("task", theTask);
        theModel.addAttribute("priorities", priorities);

        return "tasks/task-form";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute("task") Task theTask) {

        taskService.save(theTask);

        return "redirect:/tasks/list";
    }

    @GetMapping("/delete")
    public String deleteTask(@RequestParam("taskId") int theId, Model theModel) {

        taskService.deleteById(theId);

        return "redirect:/tasks/list";
    }

    @GetMapping("/changeStatus")
    public String changeStatus(@RequestParam("taskId") int theId, Model theModel) {

        Task theTask = taskService.findById(theId);
        theTask.setCompleted(!theTask.isCompleted());
        taskService.save(theTask);

        return "redirect:/tasks/list";
    }
}
