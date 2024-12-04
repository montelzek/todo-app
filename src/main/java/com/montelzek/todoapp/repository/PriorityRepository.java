package com.montelzek.todoapp.repository;

import com.montelzek.todoapp.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Integer> {
}
