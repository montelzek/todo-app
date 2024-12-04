package com.montelzek.todoapp.service;

import com.montelzek.todoapp.entity.Priority;

import java.util.List;

public interface PriorityService {

    List<Priority> findAll();
}
