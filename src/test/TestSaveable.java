package test;

import model.BusinessItem;
import model.Exception.TooManyThingsException;
import model.Exception.TooManyUrgentItemException;
import model.RegularItem;
import model.TodoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSaveable {
    private TodoList todo;

    @BeforeEach
    void setup() throws IOException {
        todo = new TodoList();
        todo.emptyFile("src/testSave");

    }

    @Test
    void testSaveItem() throws IOException, ParseException, TooManyThingsException, TooManyUrgentItemException {
        todo.loadItem("src/testSave");
        assertEquals(0, todo.size());
        todo.addItem(new RegularItem("BBBBB", "OCTOBER 1, 2018"));
        todo.addItem(new BusinessItem("CCCCC", "AUGUST 29, 2018"));
        todo.saveItem("src/testSave");
        TodoList newTodo = new TodoList();
        newTodo.loadItem("src/testSave");
        assertEquals(2,todo.size());
    }

    @Test
    void testEmptyFile() throws IOException, ParseException, TooManyThingsException, TooManyUrgentItemException {
        todo.addItem(new RegularItem("BBBBB", "OCTOBER 1, 2018"));
        todo.addItem(new BusinessItem("CCCCC", "AUGUST 29, 2018"));
        todo.saveItem("src/testSave");

        TodoList newTodo = new TodoList();
        newTodo.loadItem("src/testSave");
        assertEquals(2,todo.size());

        todo.emptyFile("src/testSave");

        newTodo = new TodoList();
        newTodo.loadItem("src/testSave");
        assertEquals(2,todo.size());
    }

    @Test
    void testSaveUser() throws IOException {
        todo.loadUserSystem("src/testSave");
        assertEquals(1, todo.getUserSystem().size());
        todo.addUser("AAAA", 0000);
        todo.addUser("BBBB", 1234);
        assertEquals(3,todo.getUserSystem().size());
        todo.saveUserSystem("src/testSave");
        TodoList newTodo = new TodoList();
        newTodo.loadUserSystem("src/testSave");
        assertEquals(3,newTodo.getUserSystem().size());
    }

}
