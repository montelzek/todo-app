package com.montelzek.todoapp.service;

import com.montelzek.todoapp.entity.Priority;
import com.montelzek.todoapp.entity.Task;
import com.montelzek.todoapp.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task task1;
    private Task task2;
    private Priority priority;

    @BeforeEach
    public void setup() {
        priority = new Priority("High", 1);
        task1 = new Task(1, "Task 1", priority, LocalDate.now().plusDays(2),
                false, LocalDateTime.now());

        task2 = new Task(2, "Task 2", priority, LocalDate.now().minusDays(2),
                true, LocalDateTime.now());
    }

    @Test
    public void findAll_ShouldReturnListOfTasks() {
        // Arrange
        List<Task> expectedTasks = List.of(task1, task2);
        when(taskRepository.findAll()).thenReturn(expectedTasks);

        // Act
        List<Task> actualTasks = taskService.findAll();

        // Assert
        assertThat(actualTasks).hasSize(2);
        assertThat(actualTasks).isEqualTo(expectedTasks);
        verify(taskRepository).findAll();
    }

    @Test
    public void findAll_shouldReturnEmptyList() {
        // Arrange
        List<Task> emptyList = List.of();
        when(taskRepository.findAll()).thenReturn(emptyList);

        // Act
        List<Task> actualTasks = taskService.findAll();

        // Assert
        assertThat(actualTasks).hasSize(0);
        verify(taskRepository).findAll();
    }

    @Test
    public void findById_existingId_shouldReturnTask() {
        // Arrange
        int taskId = task1.getId();
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task1));

        // Act
        Task foundTask = taskService.findById(taskId);

        // Assert
        assertThat(foundTask).isNotNull();
        assertThat(foundTask.getId()).isEqualTo(task1.getId());
        assertThat(foundTask.getTitle()).isEqualTo(task1.getTitle());
        assertThat(foundTask.getPriority()).isEqualTo(task1.getPriority());
        assertThat(foundTask.getDueDate()).isEqualTo(task1.getDueDate());
        assertThat(foundTask.getCreatedAt()).isEqualTo(task1.getCreatedAt());
        verify(taskRepository).findById(taskId);
    }

    @Test
    public void findById_nonExistingId_shouldThrowRuntimeException() {
        // Arrange
        int nonExistingId = -1;
        when(taskRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> taskService.findById(nonExistingId))
                .withMessage("Did not find task id - " + nonExistingId);
        verify(taskRepository).findById(nonExistingId);
    }
}
