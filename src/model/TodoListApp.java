package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class TodoListApp {
    private Scanner scanner;

    public TodoListApp() {
        scanner = new Scanner(System.in);
    }

    // REQUIRES: input must be either of 1, 2, 3, 4, 5
    // MODIFIES: todoList
    // EFFECTS: print prompts and call corresponding functions to handle user input
    public void run() throws ParseException {
        TodoList todoList = new TodoList();
        String operation = "";
        System.out.println("Welcome to TodoListApp Application, your initial password is set to '0000'");
        int passwordEntered = inputPassword();
        Boolean run = todoList.checkPasswords(passwordEntered);
        while (run) {
            System.out.println("Please select an option: " +
                    "[1] Add a Todo-list Item, " +
                    "[2] Cross-Off an Item, " +
                    "[3] View Current Todo-list, " +
                    "[4] View Overdues, " +
                    "[5] Reset Passwords, " +
                    "[6] Quit and reset Todo-list :)" );
            System.out.println("-----------------------------------------------" +
                    "-----------------------------------------------");
            operation = scanner.nextLine();
            System.out.println("you selected: [" +operation+ "]");
            int passwordEnteredAgain = inputPassword();
            run = todoList.checkPasswords(passwordEnteredAgain);
            System.out.println("-----------------------------------------------" +
                    "-----------------------------------------------");
            if (operation.equals("1")){
                Item additem = makeItem();
                todoList.addItem(additem);
            }
            if (operation.equals("2")){
                todoList.displayList();
                int index = inputIndex();
                todoList.crossedOffItem(index);
                System.out.println("your item has been crossed off!");
            }
            if (operation.equals("3")){
                todoList.displayList();
            }
            if (operation.equals("4")){
                todoList.checkOverDue();
                todoList.printOverDue();
            }
            if (operation.equals("5")){
                int newpassword = setPassword();
                todoList.resetPasswords(newpassword);
            }
            else if (operation.equals("6")) {
                run = false;
            }
        }
        System.out.println("Thank You for using Todo-list Application");

    }


    // EFFECTS: constructs a new item from user input and return it
    private Item makeItem() throws ParseException {
        System.out.println("Enter the Item Text: ");
        String newItem = scanner.nextLine();
        System.out.println("Enter the Item Due Date: ");
        System.out.println("Please Follow the Format of [MMMM d, yyyy]");
        String dateString = scanner.nextLine();
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date date = format.parse(dateString);
        return new Item(newItem, date);
    }

    // EFFECTS: returns the index from user input
    private int inputIndex(){
        System.out.println("Which item would you like to cross off?");
        int itemIndex = scanner.nextInt();
        scanner.nextLine();
        return itemIndex;
    }

    // EFFECTS: return password from user input
    private int inputPassword(){
        System.out.println("Please Enter User Password for the Todo-List Application");
        int passwordEntered = scanner.nextInt();
        scanner.nextLine();
        return passwordEntered;
    }

    // EFFECTS: return new password from user input
    private int setPassword(){
        System.out.println("Please Enter New Passwords (Numbers Only): ");
        int passwordentered = scanner.nextInt();
        scanner.nextLine();
        return passwordentered;
    }






}
