package com.montelzek.todoapp.service;

import com.montelzek.todoapp.entity.Priority;
import com.montelzek.todoapp.repository.PriorityRepository;

import java.util.List;

public class PriorityServiceImpl implements PriorityService{

    private PriorityRepository priorityRepository;

    public PriorityServiceImpl(PriorityRepository thePriorityRepository) {
        priorityRepository = thePriorityRepository;
    }

    @Override
    public List<Priority> findAll() {
        return priorityRepository.findAll();
    }
}
