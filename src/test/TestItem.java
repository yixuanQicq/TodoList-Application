package test;

import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestItem {
    private Item testItem;
    private String testDate;
    private String testName;

    @BeforeEach
    void setUp(){
        testDate = "JUNE 8, 2017";
        testName = "CPSC210 Lecture ticket";
        testItem = new Item(testName, testDate);
    }

    @Test
    void testConstructor(){
        assertEquals(testName, testItem.getName());
        assertEquals(testDate, testItem.getDueDate());
        assertEquals("In-progress", testItem.getStatus());
    }

    @Test
    void testsetName(){
        assertEquals(testName, testItem.getName());
        testItem.setName("CPSC 210 P3");
        assertEquals("CPSC 210 P3", testItem.getName());
    }

    @Test
    void testsetDate() {
        assertEquals(testDate, testItem.getDueDate());
        String newDate = "JUNE 10, 2017";
        testItem.setDueDate(newDate);

        assertEquals(newDate, testItem.getDueDate());
    }

    @Test
    void testsetStatus(){
        assertEquals("In-progress", testItem.getStatus());
        testItem.setStatus("done");
        assertEquals("done", testItem.getStatus());
    }


}
