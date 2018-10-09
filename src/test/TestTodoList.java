package test;

import model.RegularItem;
import model.TodoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TestTodoList {
    TodoList testTodoList;
    private RegularItem testRegularItem1;
    private String testDate1;
    private String testName1;
    private RegularItem testRegularItem2;
    private String testDate2;
    private String testName2;

    @BeforeEach
    void setUp() throws ParseException {
        testTodoList = new TodoList();
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        testDate1 = "JUNE 8, 2017";
        testName1 = "CPSC210 Lecture ticket";
        testRegularItem1 = new RegularItem(testName1, testDate1);
        testDate2 = "JULY 30, 2019";
        testName2 = "CPSC121 Lecture ticket";
        testRegularItem2 = new RegularItem(testName2, testDate2);
    }

    @Test
    void testAddItemTodoList() throws ParseException {
        assertEquals(0, testTodoList.size());
        testTodoList.addItem(testRegularItem1);
        assertEquals(1,testTodoList.size());
    }

    @Test
    void testCrossOff() throws ParseException {
        testTodoList.addItem(testRegularItem1);
        assertEquals("In-progress",testTodoList.getItem(0).getStatus());
        testTodoList.crossedOffItem(0);
        assertEquals("Done",testTodoList.getItem(0).getStatus());
    }

    @Test
    void tesCrossOffInvalidIndex() throws ParseException {
        testTodoList.addItem(testRegularItem1);
        assertEquals("In-progress",testTodoList.getItem(0).getStatus());
        testTodoList.crossedOffItem(8);
        assertEquals("In-progress",testTodoList.getItem(0).getStatus());
    }

    @Test
    void testCheckOverDueOneItemOverDue() throws ParseException {
        testTodoList.addItem(testRegularItem1);
        testTodoList.addItem(testRegularItem2);
        assertEquals("In-progress",testTodoList.getItem(0).getStatus());
        assertEquals("In-progress",testTodoList.getItem(1).getStatus());
        testTodoList.checkOverDue();
        assertEquals("Overdue",testTodoList.getItem(0).getStatus());
        assertEquals("In-progress",testTodoList.getItem(1).getStatus());
    }

    @Test
    void testCheckOverDueNoItemOverDue() throws ParseException {
        testTodoList.addItem(testRegularItem1);
        testTodoList.addItem(testRegularItem2);
        testTodoList.crossedOffItem(0);
        assertEquals("Done",testTodoList.getItem(0).getStatus());
        assertEquals("In-progress",testTodoList.getItem(1).getStatus());
        testTodoList.checkOverDue();
        assertEquals("Done",testTodoList.getItem(0).getStatus());
        assertEquals("In-progress",testTodoList.getItem(1).getStatus());
    }

    @Test
    void testCheckPasswords(){
        assertTrue(testTodoList.checkPasswords(0000));
        assertFalse(testTodoList.checkPasswords(3049857));
    }

    @Test
    void testResetPasswords(){
        assertTrue(testTodoList.checkPasswords(0000));
        testTodoList.resetPasswords(1234);
        assertFalse(testTodoList.checkPasswords(0000));
        assertTrue(testTodoList.checkPasswords(1234));
    }




    }
