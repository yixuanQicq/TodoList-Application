package ui;

import model.TodoListApp;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException, IOException {
        TodoListApp todoListapp = new TodoListApp();
        todoListapp.run();
    }

}
