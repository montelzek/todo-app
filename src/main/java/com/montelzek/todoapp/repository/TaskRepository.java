package com.montelzek.todoapp.repository;

import com.montelzek.todoapp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    long countByisCompleted(boolean isCompleted);
}
