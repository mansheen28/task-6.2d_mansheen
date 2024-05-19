package testtaskhd;

import static org.junit.Assert.*;
import org.junit.Test;

import taskhd.Task;
import taskhd.Unit;

import java.util.Date;
import java.util.List;

public class UnitTest {

    @Test
    public void testAddTaskAndGetTask() {
        // Right: Are the results correct?
        Unit unit = new Unit("CS101");
        Date dueDate = new Date();
        unit.addTask("Assignment1", "Complete the project", dueDate);
        assertEquals("Assignment1", unit.getTask("Assignment1").getTaskName());
        assertEquals("Complete the project", unit.getTask("Assignment1").getTaskInfo());
        assertEquals(dueDate, unit.getTask("Assignment1").getDueDate());
    }
    
    @Test
    public void testGetNonExistingTask() {
        // Boundary: Are the boundary conditions tested?
        Unit unit = new Unit("CS101");
        assertNull(unit.getTask("Assignment1"));
    }
    
    @Test
    public void testGetTaskList() {
        // Inverse: Is the inverse relationship tested?
        Unit unit = new Unit("CS101");
        unit.addTask("Assignment1", "Complete the project", new Date());
        unit.addTask("Assignment2", "Write an essay", new Date());
        
        List<String> taskList = unit.getTaskList();
        assertEquals(2, taskList.size());
        assertTrue(taskList.contains("Assignment1"));
        assertTrue(taskList.contains("Assignment2"));
    }
    
    // Cross-check: Are results cross-checked using different methods?
    @Test
    public void testCrossCheckGetTask() {
        Unit unit = new Unit("CS101");
        Date dueDate = new Date();
        unit.addTask("Assignment1", "Complete the project", dueDate);
        Task task = unit.getTask("Assignment1");
        assertNotNull(task);
        assertEquals("Assignment1", task.getTaskName());
        assertEquals("Complete the project", task.getTaskInfo());
        assertEquals(dueDate, task.getDueDate());
    }
    
    // Error: Are error conditions tested?
    @Test(expected = NullPointerException.class)
    public void testAddTaskWithNullName() {
        Unit unit = new Unit("CS101");
        Date dueDate = new Date();
        unit.addTask(null, "Complete the project", dueDate);
    }
    
    // Performance: Is the performance acceptable?
    @Test(timeout = 100)
    public void testPerformanceGetTaskList() {
        Unit unit = new Unit("CS101");
        for (int i = 0; i < 10000; i++) {
            unit.addTask("Assignment" + i, "Task " + i, new Date());
        }
        List<String> taskList = unit.getTaskList();
        assertEquals(10000, taskList.size());
    }
}

