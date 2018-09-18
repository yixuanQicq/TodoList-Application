package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TodoList {
    Boolean run  = true;
    Scanner scanner = new Scanner(System.in);

    ArrayList<String> todoList;
    ArrayList<String> crossedOffList;

    public TodoList(){
        todoList = new ArrayList<>();
        crossedOffList = new ArrayList<>();
        String operation = "";
        while (run) {
            System.out.println("Please select an option: " +
                    "[1] add a todo list item, " +
                    "[2] cross off an item, " +
                    "[3] view current todo list, " +
                    "[4] quit todo-list");
            operation = scanner.nextLine();
            System.out.println("you selected: [" +operation+ "]" );

            if (operation.equals("1")){
                System.out.println("Enter the item text");
                addItem();
            }
            if (operation.equals("2")){
                displayList("Todo List: ",todoList);
                System.out.println("Which item would you like to cross off?");
                crossedOffItem();
                System.out.println("your item has been crossed off!");
            }
            if (operation.equals("3")){
                displayList("Todo List: ",todoList);
                displayList("Crossed Off List: ",crossedOffList);
            }
            else if (operation.equals("4")){
                run = quit();
            }
        }
        System.out.println("Thank You for using Todo List");
    }

    public Boolean quit() {
        return false;
    }

    public void addItem(){
        String newitem = scanner.nextLine();
        todoList.add(newitem);
    }

    public void crossedOffItem(){
        int itemIndex = scanner.nextInt();
        String item = todoList.get(itemIndex);
        scanner.nextLine();
        crossedOffList.add(item);
        todoList.remove(itemIndex);
    }
    
    public void displayList(String prompt, List<String> lst){
        for (String s: lst) {
            System.out.println(prompt+ "[" + lst.indexOf(s) + "]" + s);
        }
        System.out.println("---------------------");
    }

    public static void main(String[] args) {
        displayHi();
        new TodoList();
    }

    private static void displayHi(){
        System.out.println("Hi!");
    }

}
