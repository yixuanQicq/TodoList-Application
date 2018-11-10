package ui;

import model.TodoListApp;
import model.WebPageReader;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException, IOException {
        WebPageReader welcomeMessage = WebPageReader.getInstance();
        welcomeMessage.greetingMessage();
        TodoListApp todoListapp = new TodoListApp();
        todoListapp.run();
    }

}
