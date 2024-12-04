package com.montelzek.todoapp.service;

import com.montelzek.todoapp.entity.Priority;
import com.montelzek.todoapp.repository.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityServiceImpl implements PriorityService{

    private final PriorityRepository priorityRepository;

    @Autowired
    public PriorityServiceImpl(PriorityRepository thePriorityRepository) {
        priorityRepository = thePriorityRepository;
    }

    @Override
    public List<Priority> findAll() {
        return priorityRepository.findAll();
    }
}
