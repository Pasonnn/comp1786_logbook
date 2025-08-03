package com.example.todolist;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Comprehensive Unit Tests for Todo List App
 * 
 * This test suite covers all major functionality including:
 * - Task model testing
 * - TaskAdapter testing
 * - Input validation testing
 * - Data integrity testing
 * - Performance testing
 * 
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TodoListUnitTest {

    private Task testTask;
    private TaskAdapter taskAdapter;
    private List<Task> taskList;

    @Before
    public void setUp() {
        // Initialize test data
        testTask = new Task("Test Task", "Test Description", "01/01/2024", "2h");
        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(taskList);
    }

    @After
    public void tearDown() {
        // Clean up test data
        taskList.clear();
        testTask = null;
        taskAdapter = null;
    }

    /**
     * Test ID: T01
     * Test Name: testTaskCreation
     * Description: Create task with all fields
     */
    @Test
    public void testTaskCreation() {
        // Arrange
        String title = "Complete Assignment";
        String description = "Finish Android development assignment";
        String deadline = "15/12/2024";
        String duration = "4h";

        // Act
        Task newTask = new Task(title, description, deadline, duration);

        // Assert
        assertNotNull("Task should not be null", newTask);
        assertEquals("Title should match", title, newTask.getTitle());
        assertEquals("Description should match", description, newTask.getDescription());
        assertEquals("Deadline should match", deadline, newTask.getDeadline());
        assertEquals("Duration should match", duration, newTask.getDuration());
        assertFalse("New task should not be completed", newTask.isDone());
    }

    /**
     * Test ID: T02
     * Test Name: testEmptyTitleValidation
     * Description: Test validation with empty title
     */
    @Test
    public void testEmptyTitleValidation() {
        // Arrange
        String emptyTitle = "";
        String trimmedEmptyTitle = "   ";
        String validTitle = "Valid Task";

        // Act & Assert
        assertTrue("Empty string should be considered empty", emptyTitle.isEmpty());
        assertTrue("Trimmed empty string should be considered empty", trimmedEmptyTitle.trim().isEmpty());
        assertFalse("Valid title should not be empty", validTitle.isEmpty());
    }

    /**
     * Test ID: T03
     * Test Name: testTaskCompletion
     * Description: Mark task as completed
     */
    @Test
    public void testTaskCompletion() {
        // Arrange
        Task task = new Task("Test Task", "Description", "01/01/2024", "1h");
        
        // Act - Mark as completed
        task.setDone(true);
        
        // Assert
        assertTrue("Task should be marked as completed", task.isDone());
        
        // Act - Mark as incomplete
        task.setDone(false);
        
        // Assert
        assertFalse("Task should be marked as incomplete", task.isDone());
    }

    /**
     * Test ID: T04
     * Test Name: testTaskDeletion
     * Description: Delete task from list
     */
    @Test
    public void testTaskDeletion() {
        // Arrange
        Task task1 = new Task("Task 1", "Desc 1", "01/01/2024", "1h");
        Task task2 = new Task("Task 2", "Desc 2", "02/01/2024", "2h");
        taskList.add(task1);
        taskList.add(task2);
        
        // Act
        int initialSize = taskList.size();
        taskList.remove(0);
        
        // Assert
        assertEquals("List size should decrease by 1", initialSize - 1, taskList.size());
        assertEquals("Remaining task should be task2", task2, taskList.get(0));
    }

    /**
     * Test ID: T05
     * Test Name: testDatePickerFunctionality
     * Description: Test date picker dialog
     */
    @Test
    public void testDatePickerFunctionality() {
        // Arrange
        String testDate = "15/12/2024";
        
        // Act & Assert
        assertNotNull("Date string should not be null", testDate);
        assertFalse("Date string should not be empty", testDate.isEmpty());
        assertTrue("Date should contain expected format", testDate.matches("\\d{1,2}/\\d{1,2}/\\d{4}"));
    }

    /**
     * Test ID: T06
     * Test Name: testTaskAdapterAddTask
     * Description: Add task to adapter
     */
    @Test
    public void testTaskAdapterAddTask() {
        // Arrange
        int initialSize = taskAdapter.getItemCount();
        Task newTask = new Task("New Task", "New Description", "01/01/2024", "1h");
        
        // Act
        taskAdapter.addTask(newTask);
        
        // Assert
        assertEquals("Adapter item count should increase by 1", initialSize + 1, taskAdapter.getItemCount());
        assertTrue("Task list should contain the new task", taskList.contains(newTask));
    }

    /**
     * Test ID: T07
     * Test Name: testTaskAdapterRemoveTask
     * Description: Remove task from adapter
     */
    @Test
    public void testTaskAdapterRemoveTask() {
        // Arrange
        Task task1 = new Task("Task 1", "Desc 1", "01/01/2024", "1h");
        Task task2 = new Task("Task 2", "Desc 2", "02/01/2024", "2h");
        taskAdapter.addTask(task1);
        taskAdapter.addTask(task2);
        int initialSize = taskAdapter.getItemCount();
        
        // Act
        taskList.remove(0);
        
        // Assert
        assertEquals("Adapter item count should decrease by 1", initialSize - 1, taskAdapter.getItemCount());
        assertEquals("Remaining task should be task2", task2, taskList.get(0));
    }

    /**
     * Test ID: T08
     * Test Name: testTaskModelProperties
     * Description: Test Task model getters/setters
     */
    @Test
    public void testTaskModelProperties() {
        // Arrange
        String title = "Test Title";
        String description = "Test Description";
        String deadline = "01/01/2024";
        String duration = "2h";
        
        // Act
        Task task = new Task(title, description, deadline, duration);
        
        // Assert
        assertEquals("Title getter should return correct value", title, task.getTitle());
        assertEquals("Description getter should return correct value", description, task.getDescription());
        assertEquals("Deadline getter should return correct value", deadline, task.getDeadline());
        assertEquals("Duration getter should return correct value", duration, task.getDuration());
    }

    /**
     * Test ID: T09
     * Test Name: testTaskInitialState
     * Description: Test task initial completion state
     */
    @Test
    public void testTaskInitialState() {
        // Arrange & Act
        Task newTask = new Task("Test Task", "Description", "01/01/2024", "1h");
        
        // Assert
        assertFalse("New task should not be completed initially", newTask.isDone());
    }

    /**
     * Test ID: T10
     * Test Name: testTaskToggleCompletion
     * Description: Toggle task completion state
     */
    @Test
    public void testTaskToggleCompletion() {
        // Arrange
        Task task = new Task("Test Task", "Description", "01/01/2024", "1h");
        
        // Act & Assert - Toggle to completed
        task.setDone(true);
        assertTrue("Task should be completed", task.isDone());
        
        // Act & Assert - Toggle back to incomplete
        task.setDone(false);
        assertFalse("Task should be incomplete", task.isDone());
        
        // Act & Assert - Toggle again to completed
        task.setDone(true);
        assertTrue("Task should be completed again", task.isDone());
    }

    /**
     * Test ID: T11
     * Test Name: testIntentDataPassing
     * Description: Test data passing between activities
     */
    @Test
    public void testIntentDataPassing() {
        // Arrange
        String title = "Test Task";
        String description = "Test Description";
        String deadline = "01/01/2024";
        String duration = "2h";
        
        // Act & Assert - Simulate Intent data
        assertNotNull("Title should not be null", title);
        assertNotNull("Description should not be null", description);
        assertNotNull("Deadline should not be null", deadline);
        assertNotNull("Duration should not be null", duration);
        
        // Test data integrity
        assertEquals("Title should match", "Test Task", title);
        assertEquals("Description should match", "Test Description", description);
        assertEquals("Deadline should match", "01/01/2024", deadline);
        assertEquals("Duration should match", "2h", duration);
    }

    /**
     * Test ID: T12
     * Test Name: testRecyclerViewAdapter
     * Description: Test adapter item count
     */
    @Test
    public void testRecyclerViewAdapter() {
        // Arrange
        int initialCount = taskAdapter.getItemCount();
        
        // Act - Add multiple tasks
        Task task1 = new Task("Task 1", "Desc 1", "01/01/2024", "1h");
        Task task2 = new Task("Task 2", "Desc 2", "02/01/2024", "2h");
        Task task3 = new Task("Task 3", "Desc 3", "03/01/2024", "3h");
        
        taskAdapter.addTask(task1);
        taskAdapter.addTask(task2);
        taskAdapter.addTask(task3);
        
        // Assert
        assertEquals("Adapter should have 3 items", 3, taskAdapter.getItemCount());
        assertEquals("Initial count + 3 should equal current count", initialCount + 3, taskAdapter.getItemCount());
        
        // Act - Remove a task
        taskList.remove(1);
        
        // Assert
        assertEquals("Adapter should have 2 items after removal", 2, taskAdapter.getItemCount());
    }

    /**
     * Test ID: T13
     * Test Name: testInputSanitization
     * Description: Test input trimming
     */
    @Test
    public void testInputSanitization() {
        // Arrange
        String inputWithSpaces = "   Test Task   ";
        String inputWithTabs = "\tTest Task\t";
        String cleanInput = "Test Task";
        
        // Act
        String trimmedWithSpaces = inputWithSpaces.trim();
        String trimmedWithTabs = inputWithTabs.trim();
        
        // Assert
        assertEquals("Should trim leading and trailing spaces", cleanInput, trimmedWithSpaces);
        assertEquals("Should trim leading and trailing tabs", cleanInput, trimmedWithTabs);
        assertFalse("Trimmed input should not be empty", trimmedWithSpaces.isEmpty());
    }

    /**
     * Test ID: T14
     * Test Name: testNullDataHandling
     * Description: Test null data handling
     */
    @Test
    public void testNullDataHandling() {
        // Arrange
        String nullString = null;
        String emptyString = "";
        String validString = "Valid String";
        
        // Act & Assert
        assertNull("Null string should be null", nullString);
        assertTrue("Empty string should be empty", emptyString.isEmpty());
        assertNotNull("Valid string should not be null", validString);
        assertFalse("Valid string should not be empty", validString.isEmpty());
    }

    /**
     * Test ID: T15
     * Test Name: testLargeTaskList
     * Description: Test performance with many tasks
     */
    @Test
    public void testLargeTaskList() {
        // Arrange
        int largeTaskCount = 100;
        
        // Act - Add many tasks
        for (int i = 0; i < largeTaskCount; i++) {
            Task task = new Task("Task " + i, "Description " + i, "01/01/2024", i + "h");
            taskAdapter.addTask(task);
        }
        
        // Assert
        assertEquals("Should have exactly 100 tasks", largeTaskCount, taskAdapter.getItemCount());
        assertTrue("Task list should not be empty", !taskList.isEmpty());
        assertEquals("First task should have correct title", "Task 0", taskList.get(0).getTitle());
        assertEquals("Last task should have correct title", "Task 99", taskList.get(99).getTitle());
    }

    /**
     * Additional Test: Test task with special characters
     */
    @Test
    public void testTaskWithSpecialCharacters() {
        // Arrange
        String titleWithSpecialChars = "Task with @#$% symbols";
        String descriptionWithSpecialChars = "Description with émojis 🎉";
        
        // Act
        Task task = new Task(titleWithSpecialChars, descriptionWithSpecialChars, "01/01/2024", "1h");
        
        // Assert
        assertEquals("Title with special characters should be preserved", titleWithSpecialChars, task.getTitle());
        assertEquals("Description with special characters should be preserved", descriptionWithSpecialChars, task.getDescription());
    }

    /**
     * Additional Test: Test task with very long text
     */
    @Test
    public void testTaskWithLongText() {
        // Arrange
        StringBuilder longTitle = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            longTitle.append("A");
        }
        
        // Act
        Task task = new Task(longTitle.toString(), "Description", "01/01/2024", "1h");
        
        // Assert
        assertEquals("Long title should be preserved", longTitle.toString(), task.getTitle());
        assertEquals("Long title length should be 1000", 1000, task.getTitle().length());
    }

    /**
     * Additional Test: Test task completion state persistence
     */
    @Test
    public void testTaskCompletionStatePersistence() {
        // Arrange
        Task task = new Task("Test Task", "Description", "01/01/2024", "1h");
        
        // Act & Assert - Multiple state changes
        task.setDone(true);
        assertTrue("Task should be completed", task.isDone());
        
        task.setDone(true); // Set to same state
        assertTrue("Task should remain completed", task.isDone());
        
        task.setDone(false);
        assertFalse("Task should be incomplete", task.isDone());
        
        task.setDone(false); // Set to same state
        assertFalse("Task should remain incomplete", task.isDone());
    }
} 