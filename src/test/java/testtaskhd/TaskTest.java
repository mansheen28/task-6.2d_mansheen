package testtaskhd;


import static org.junit.Assert.*;
import org.junit.Test;

import taskhd.Task;

import java.util.Date;
import java.util.List;

public class TaskTest {

    @Test
    public void testAddChatMessageAndGetChatMessages() {
        Task task = new Task("Assignment1", "Complete the project", new Date());
        task.addChatMessage("Need clarification on task requirements");
        task.addChatMessage("When is the deadline?");
        
        List<String> chatMessages = task.getChatMessages();
        assertEquals(2, chatMessages.size());
        assertTrue(chatMessages.contains("Need clarification on task requirements"));
        assertTrue(chatMessages.contains("When is the deadline?"));
    }
    
    @Test
    public void testUpdateStatusAndGetStatus() {
        Task task = new Task("Assignment1", "Complete the project", new Date());
        task.updateStatus("In Progress");
        assertEquals("In Progress", task.getStatus());
    }
    
    @Test
    public void testAddSubmissionAndGetSubmissions() {
        Task task = new Task("Assignment1", "Complete the project", new Date());
        task.addSubmission("Submission 1");
        task.addSubmission("Submission 2");
        
        List<String> submissions = task.getSubmissions();
        assertEquals(2, submissions.size());
        assertTrue(submissions.contains("Submission 1"));
        assertTrue(submissions.contains("Submission 2"));
    }
    
    @Test
    public void testRequestExtensionAndGetExtensionRequest() {
        Task task = new Task("Assignment1", "Complete the project", new Date());
        Date newDueDate = new Date();
        task.requestExtension(newDueDate);
        
        assertEquals(newDueDate, task.getExtensionRequest());
    }
}
