package test;

import model.Item;
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
    void testSave() throws IOException, ParseException {
        todo.load("src/testSave");
        assertEquals(0, todo.size());
        todo.addItem(new Item("BBBBB", "OCTOBER 1, 2018"));
        todo.addItem(new Item("CCCCC", "AUGUST 29, 2018"));
        todo.save("src/testSave");
        TodoList newTodo = new TodoList();
        newTodo.load("src/testSave");
        assertEquals(2,todo.size());
    }

    @Test
    void testEmptyFile() throws IOException, ParseException {
        todo.addItem(new Item("BBBBB", "OCTOBER 1, 2018"));
        todo.addItem(new Item("CCCCC", "AUGUST 29, 2018"));
        todo.save("src/testSave");

        TodoList newTodo = new TodoList();
        newTodo.load("src/testSave");
        assertEquals(2,todo.size());

        todo.emptyFile("src/testSave");

        newTodo = new TodoList();
        newTodo.load("src/testSave");
        assertEquals(2,todo.size());

    }




}
