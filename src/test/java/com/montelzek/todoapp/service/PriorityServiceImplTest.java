package com.montelzek.todoapp.service;

import com.montelzek.todoapp.entity.Priority;
import com.montelzek.todoapp.repository.PriorityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriorityServiceImplTest {

    @Mock
    private PriorityRepository priorityRepository;

    @InjectMocks
    private PriorityServiceImpl priorityService;

    @Test
    public void findAll_ShouldReturnListOFPriorities() {
        // Arrange
        Priority priority1 = new Priority("High", 1);
        Priority priority2 = new Priority("Medium", 2);

        List<Priority> expectedPriorities = List.of(priority1, priority2);
        when(priorityRepository.findAll()).thenReturn(expectedPriorities);

        // Act
        List<Priority> actualPriorities = priorityService.findAll();

        // Assert
        assertThat(actualPriorities).hasSize(2);
        assertThat(actualPriorities).isEqualTo(expectedPriorities);
    }

    @Test
    public void findAll_shouldReturnEmptyList() {
        // Arrange
        List<Priority> emptyList = List.of();
        when(priorityRepository.findAll()).thenReturn(emptyList);

        // Act
        List<Priority> actualPriorities = priorityService.findAll();

        // Assert
        assertThat(actualPriorities).hasSize(0);
    }
}
