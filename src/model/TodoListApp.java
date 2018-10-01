package model;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;


public class TodoListApp {
    private Scanner scanner;

    public TodoListApp() {
        scanner = new Scanner(System.in);
    }

    // REQUIRES: input must be either of 1, 2, 3, 4, 5
    // MODIFIES: todoList
    // EFFECTS: print prompts and call corresponding functions to handle user input
    public void run() throws ParseException, IOException {
        TodoList todoList = new TodoList();
        String operation = "";
        //System.out.println("Welcome to TodoListApp Application, your initial password is set to '0000'");
        int passwordEntered = inputPassword();
        Boolean run = todoList.checkPasswords(passwordEntered);
        todoList.load("src/savefile.txt");
        while (run) {
            System.out.println("Please select an option: " +
                    "[1] Add a Todo-list Item, " +
                    "[2] Cross-Off an Item, " +
                    "[3] View Current Todo-list, " +
                    "[4] View Overdues, " +
                    "[5] Reset Passwords, " +
                    "[6] Empty Todolist, " +
                    "[7] Save File and Quit TodoList");
            System.out.println("-----------------------------------------------" +
                    "-----------------------------------------------");
            operation = scanner.nextLine();
            System.out.println("you selected: [" +operation+ "]");
            //int passwordEnteredAgain = inputPassword();
            //run = todoList.checkPasswords(passwordEnteredAgain);
            System.out.println("-----------------------------------------------" +
                    "-----------------------------------------------");
            if (operation.equals("1")){
                Item additem = makeItem();
                todoList.addItem(additem);
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
                todoList.checkOverDue();
                printOverDue(todoList.getTodoList());
            }
            if (operation.equals("5")){
                int newpassword = setPassword();
                todoList.resetPasswords(newpassword);
            }
            if (operation.equals("6")){
                todoList = new TodoList();
                todoList.emptyFile("src/savefile.txt");
                System.out.println("Todo-list has been Emptied.");
            }
            else if (operation.equals("7")) {
                todoList.save("src/savefile.txt");
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
        // DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        // Date date = format.parse(dateString);
        return new Item(newItem, dateString);
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

    // EFFECTS: print out todolist
    private void displayList(ArrayList<Item> todoList){
        System.out.println("TODO-LIST:");
        for (Item s: todoList) {
            System.out.println("[" + todoList.indexOf(s) + "]:  " + s.getName()+ " --  Status: "+s.getStatus());
        }
        System.out.println("-----------------------------------------------" +
                "-----------------------------------------------");

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
            System.out.println("-----------------------------------------------" +
                    "-----------------------------------------------");
        }
    }






}
