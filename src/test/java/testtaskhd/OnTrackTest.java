package testtaskhd;


import static org.junit.Assert.*;
import org.junit.Test;

import taskhd.OnTrack;

import java.util.Date;
import java.util.List;

public class OnTrackTest {

    @Test
    public void testEnrollUnitAndListEnrolledUnits() {
        // Right: Are the results correct?
        OnTrack onTrack = new OnTrack();
        onTrack.enrollUnit("student1", "CS101");
        
        List<String> enrolledUnits = onTrack.listEnrolledUnits("student1");
        assertEquals(1, enrolledUnits.size());
        assertTrue(enrolledUnits.contains("CS101"));
    }
    
    @Test
    public void testListTasks() {
        // Boundary: Are the boundary conditions tested?
        OnTrack onTrack = new OnTrack();
        onTrack.enrollUnit("student1", "CS101");
        onTrack.getUnit("student1", "CS101").addTask("Assignment1", "Complete the project", new Date());
        
        List<String> tasks = onTrack.listTasks("student1", "CS101");
        assertEquals(1, tasks.size());
        assertTrue(tasks.contains("Assignment1"));
    }
    
    @Test
    public void testGetTaskInformation() {
        // Inverse: Is the inverse relationship tested?
        OnTrack onTrack = new OnTrack();
        onTrack.enrollUnit("student1", "CS101");
        onTrack.getUnit("student1", "CS101").addTask("Assignment1", "Complete the project", new Date());
        
        String taskInfo = onTrack.getTaskInformation("student1", "CS101", "Assignment1");
        assertTrue(taskInfo.contains("Task Info"));
        assertTrue(taskInfo.contains("Due Date"));
        assertTrue(taskInfo.contains("Status"));
    }
    
    @Test
    public void testChatWithProfessor() {
        // Cross-check: Are results cross-checked using different methods?
        OnTrack onTrack = new OnTrack();
        onTrack.enrollUnit("student1", "CS101");
        onTrack.getUnit("student1", "CS101").addTask("Assignment1", "Complete the project", new Date());
        
        onTrack.chatWithProfessor("student1", "CS101", "Assignment1", "Need help with this assignment");
        
        List<String> chatMessages = onTrack.getUnit("student1", "CS101").getTask("Assignment1").getChatMessages();
        assertEquals(1, chatMessages.size());
        assertTrue(chatMessages.contains("Need help with this assignment"));
    }
    
    @Test
    public void testUpdateTaskStatus() {
        // Error: Are error conditions tested?
        OnTrack onTrack = new OnTrack();
        onTrack.enrollUnit("student1", "CS101");
        onTrack.getUnit("student1", "CS101").addTask("Assignment1", "Complete the project", new Date());
        
        onTrack.updateTaskStatus("student1", "CS101", "Assignment1", "In Progress");
        
        assertEquals("In Progress", onTrack.getUnit("student1", "CS101").getTask("Assignment1").getStatus());
    }
    
    @Test
    public void testMakeSubmission() {
        // Performance: Is the performance acceptable?
        OnTrack onTrack = new OnTrack();
        onTrack.enrollUnit("student1", "CS101");
        onTrack.getUnit("student1", "CS101").addTask("Assignment1", "Complete the project", new Date());
        
        onTrack.makeSubmission("student1", "CS101", "Assignment1", "Here is my project submission");
        
        List<String> submissions = onTrack.getUnit("student1", "CS101").getTask("Assignment1").getSubmissions();
        assertEquals(1, submissions.size());
        assertTrue(submissions.contains("Here is my project submission"));
    }
    
    @Test
    public void testRequestExtension() {
        // Right: Are the results correct?
        OnTrack onTrack = new OnTrack();
        onTrack.enrollUnit("student1", "CS101");
        onTrack.getUnit("student1", "CS101").addTask("Assignment1", "Complete the project", new Date());
        
        Date newDueDate = new Date();
        onTrack.requestExtension("student1", "CS101", "Assignment1", newDueDate);
        
        assertEquals(newDueDate, onTrack.getUnit("student1", "CS101").getTask("Assignment1").getExtensionRequest());
    }
    
}


