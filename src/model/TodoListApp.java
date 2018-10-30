package model;

import model.Exception.DateIncorrectFormatException;
import model.Exception.TodoListException;
import java.io.IOException;
import java.util.*;

public class TodoListApp {
    private Scanner scanner;

    public TodoListApp() {
        scanner = new Scanner(System.in);
    }

    // REQUIRES: input must be either of 1, 2, 3, 4, 5
    // MODIFIES: todoList
    // EFFECTS: print prompts and call corresponding functions to handle user input
    public void run() {
        TodoList todoList = new TodoList();

        String operation = "";
        String nameEntered = inputUsername();
        int passwordEntered = inputPassword();
        try {
            todoList.loadItem("src/savefile.txt");
            todoList.loadUserSystem("src/userfile");
        } catch (IOException e) {
            System.out.println("File does not exist, please create the file before running the program!");
        }

        User currentUser = new User(nameEntered);
        currentUser.setPasswords(new Password(passwordEntered));
        Boolean run = todoList.accessVerification(passwordEntered,nameEntered);

        while (run) {
            scanner.nextLine();
            optionLog();
            operation = scanner.nextLine();
            System.out.println("you selected: [" +operation+ "]");
            System.out.println("-----------------------------------------------");
            if (operation.equals("1")){
                Item additem = makeItem();
                try {
                    todoList.addItem(additem);
                } catch (TodoListException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (operation.equals("2")){
                displayList(todoList.getTodoList());
                int index = inputIndex();
                todoList.crossedOffItem(index);
                System.out.println("your item has been crossed off!");
            }
            if (operation.equals("3")){
                displayList(todoList.getTodoList());
            }
            if (operation.equals("4")){
                try {
                    todoList.checkOverDue();
                } catch (DateIncorrectFormatException e) {
                    System.out.println("ERROR. DATE FORMAT IS INCORRECT.");

                }
                printOverDue(todoList.getTodoList());
            }
            if (operation.equals("5")){
                displayList(todoList.getTodoList());
                resetDueDate(todoList);
            }
            if (operation.equals("6")){
                int newpassword = setPassword();
                todoList.resetPasswords(currentUser,newpassword);
            }
            if (operation.equals("7")){
                todoList = new TodoList();
                try {
                    todoList.emptyFile("src/savefile.txt");
                } catch (IOException e) {
                    System.out.println("This should never happen, I know this file exists");
                } finally {
                    System.out.println("Todo-list has been Emptied.");
                }
            }
            if (operation.equals("8")){
                addNewUser(todoList);
            }
            else if (operation.equals("9")) {
                try {
                    todoList.saveItem("src/savefile.txt");
                    todoList.saveUserSystem("src/userfile");
                } catch (IOException e) {
                    System.out.println("This should never happen, I know this file exists");
                } finally {
                    run = false;
                }
            }
        }
        System.out.println("Thank You for using Todo-list Application");
    }


    private void optionLog(){
        System.out.println("Please select an option: ");
        System.out.println("[1] Add a Todo-list Item ");
        System.out.println("[2] Cross-Off an Item ");
        System.out.println("[3] View Current Todo-list ");
        System.out.println("[4] View Overdues ");
        System.out.println("[5] Reset Item Due Dates ");
        System.out.println("[6] Reset Passwords ");
        System.out.println("[7] Empty Todolist ");
        System.out.println("[8] Add A New User to the System");
        System.out.println("[9] Save File and Quit TodoList");
        System.out.println("-----------------------------------------------");
    }



    // EFFECTS: constructs a new item from user input and return it
    private Item makeItem() {
        System.out.println("Please select the type of Item you wish to create: ");
        System.out.println("[1] Urgent Item, [2] Regular Item, [3] Business Item ");
        System.out.println("-----------------------------------------------");
        String typeSelection = scanner.nextLine();
        System.out.println("Enter the Item Text: ");
        String newItem = scanner.nextLine();
        System.out.println("Enter the Item Due Date: ");
        System.out.println("Please Follow the Format of [MMMM d, yyyy]");
        String dateString = scanner.nextLine();
        if (typeSelection.equals("1")){
            return new UrgentItem(newItem, dateString);
        }
        if (typeSelection.equals("2")){
            return new RegularItem(newItem, dateString);
        }
        return new BusinessItem(newItem, dateString);
    }

    private void resetDueDate(TodoList todoList){
        System.out.println("Please select the item you want to change due-date");
        int itemIndex = scanner.nextInt();
        scanner.nextLine();
        Item item = todoList.getItem(itemIndex);
        System.out.println("Please enter new Item due-date");
        String newDueDate = scanner.nextLine();
        item.setDueDate(newDueDate);
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

    private String inputUsername(){
        System.out.println("Please Enter Username for the Todo-List Application");
        String nameEntered = scanner.nextLine();
        return nameEntered;
    }

    // EFFECTS: return new password from user input
    private int setPassword(){
        System.out.println("Please Enter New Passwords (Numbers Only): ");
        int passwordentered = scanner.nextInt();
        scanner.nextLine();
        return passwordentered;
    }

    // EFFECTS: print out todolist
    private void displayList(ArrayList<Item> todoList){
        todoList.sort(Comparator.comparing(Item::getItemType).reversed());
        System.out.println("TODO-LIST:");
        for (Item s: todoList) {
            System.out.println("[" + todoList.indexOf(s) + "]:  " +
                    s.getItemType() + "  " +
                    s.getName() +
                    " --  Status: " + s.getStatus());
        }
        System.out.println("-----------------------------------------------");
    }

    // EFFECTS: print out list of over due items
    private void printOverDue(ArrayList<Item> todoList){
        ArrayList<Item> overDue = new ArrayList<>();
        for (Item i: todoList){
            if((i.getStatus().equals("Overdue"))){
                overDue.add(i);
            }
        }
        if (overDue.size() > 0){
            System.out.println("OVER DUE ITEMS: ");
            for (Item i: overDue){
                System.out.println(i.getName() + "-- Due: "+ i.getDueDate());
            }
            System.out.println("-----------------------------------------------");
        }
    }

    private void addNewUser(TodoList todoList){
        System.out.println("Please enter new User Name");
        String newUserName = scanner.nextLine();
        System.out.println("Please enter new User Password");
        int newPassWordInt = scanner.nextInt();
        scanner.nextLine();

        todoList.addUser(newUserName, newPassWordInt);
    }

}
