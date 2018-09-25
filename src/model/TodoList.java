package model;


import java.text.ParseException;
import java.util.*;

public class TodoList {
    private ArrayList<Item> todoList;

    private Scanner scanner = new Scanner(System.in);
    private Date currentDate = new Date();
    private User user = new User();

    public TodoList (){
        todoList = new ArrayList<>();
    }

    // REQUIRES: user input according to required format
    // MODIFIES: this
    // EFFECTS: add a new item to the list, throws ParseException if date input is in wrong format
    public void addItem(Item i) throws ParseException {
        todoList.add(i);
    }

    // MODIFIES: Item
    // EFFECTS: change the status of item at itemIndex to done, print error message if index is invalid
    public void crossedOffItem(int itemIndex){
        if (itemIndex >=0 && itemIndex < todoList.size()) {
            Item item = todoList.get(itemIndex);
            item.setStatus("Done");
        } else {
            System.out.println("Invalid Index");
        }
    }

    // EFFECTS: print out todolist
    public void displayList(){
        System.out.println("TODO-LIST:");
        for (Item s: todoList) {
            System.out.println("[" + todoList.indexOf(s) + "]:  " + s.getName()+ " --  Status: "+s.getStatus());
        }
        System.out.println("-----------------------------------------------" +
                "-----------------------------------------------");

    }

    // MODIFIES: Item
    // EFFECTS: set the status of the item to overdue
    public void checkOverDue() {
        for (Item i: todoList){
            if (i.getDueDate().before(currentDate)){
                if(!(i.getStatus().equals("Done"))){
                    i.setStatus("Overdue");
                }
            }
        }
    }

    // EFFECTS: print out list of over due items
    public void printOverDue(){
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

    // EFFECTS: return true if password is correct, false otherwise
    public Boolean checkPasswords(int passwordEntered) {
        if (user.getPasswords() == passwordEntered) {
            return true;
        }
        System.out.println("ERROR: PASSWORD INCORRECT");
        return false;
    }

    // MODIFIES: User
    // EFFECTS: set user password to passwordentered
    public void resetPasswords(int passwordentered) {
        user.setPasswords(passwordentered);
    }

    // EFFECTS: return size of the todolist
    public int size(){
        return todoList.size();
    }


    // EFFECTS: return item at index i of the todolist
    public Item getItem(int i){
        return todoList.get(i);
    }



}
