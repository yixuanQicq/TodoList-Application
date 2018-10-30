package test;

import model.TodoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLoadable {
    private TodoList todo;

    @BeforeEach
    void setup(){
        todo = new TodoList();

    }

    @Test
    void testLoadFromFile() throws IOException {
        assertEquals(0,todo.size());
        todo.loadItem("src/testLoad");
        assertEquals(3,todo.size());
        assertEquals("AAAAA",todo.getItem(0).getName());
        assertEquals("BBBBB",todo.getItem(1).getName());
        assertEquals("CCCCC",todo.getItem(2).getName());

    }
}
