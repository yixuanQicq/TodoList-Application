package test;

import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestItem {
    private Item testItem;
    private Date testDate;
    private String testName;

    @BeforeEach
    void setUp() throws ParseException {
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        testDate = format.parse("JUNE 8, 2017");
        testName = "CPSC210 Lecture ticket";
        testItem = new Item(testName, testDate);
    }

    @Test
    void testConstructor() throws ParseException {
        assertEquals(testName, testItem.getName());
        assertEquals(testDate, testItem.getDueDate());
        assertEquals("in progress", testItem.getStatus());
    }

    @Test
    void testsetName(){
        assertEquals(testName, testItem.getName());
        testItem.setName("CPSC 210 P3");
        assertEquals("CPSC 210 P3", testItem.getName());
    }

    @Test
    void testsetDate() throws ParseException {
        assertEquals(testDate, testItem.getDueDate());
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date newDate = format.parse("JUNE 10, 2017");
        testItem.setDueDate(newDate);

        assertEquals(newDate, testItem.getDueDate());
    }

    @Test
    void testsetStatus(){
        assertEquals("in progress", testItem.getStatus());
        testItem.setStatus("done");
        assertEquals("done", testItem.getStatus());
    }


}
