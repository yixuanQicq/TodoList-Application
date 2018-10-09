package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TodoList implements Loadable, Saveable {
    private ArrayList<Item> todoList;
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

    // MODIFIES: RegularItem
    // EFFECTS: change the status of item at itemIndex to done, print error message if index is invalid
    public void crossedOffItem(int itemIndex){
        if (itemIndex >=0 && itemIndex < todoList.size()) {
            Item regularItem = todoList.get(itemIndex);
            regularItem.setStatus("Done");
        } else {
            System.out.println("Invalid Index");
        }
    }

    // MODIFIES: RegularItem
    // EFFECTS: set the status of the item to overdue
    public void checkOverDue() throws ParseException {
        for (Item i: todoList){
            DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
            Date date = format.parse(i.getDueDate());
            if (date.before(currentDate)){
                if(!(i.getStatus().equals("Done"))){
                    i.setStatus("Overdue");
                }
            }
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

    public ArrayList<Item> getTodoList() {
        return todoList;
    }

    // REQUIRES: input file name to exist
    // MODIFIES: this
    // EFFECTS: print in todolist item in file
    @Override
    public void save(String fileName) throws IOException {
        List<String> newLines = new ArrayList<>();
        PrintWriter writer = new PrintWriter("src/savefile.txt","UTF-8");
            for (Item i : todoList) {
                newLines.add(i.getName() + " : " + i.getStatus() + " : " +
                        i.getDueDate() + " : " + i.getItemType());
            }
            for (String line : newLines){
                    writer.println(line);
                }
        writer.close();
    }

    // REQUIRES: input file name to exist
    // EFFECTS: print file items into todolist
    @Override
    public void load(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        for (String line: lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            if (partsOfLine.get(3).equals("Urgent")){
            todoList.add(new UrgentItem(partsOfLine.get(0),partsOfLine.get(1), partsOfLine.get(2)));
            }
            if (partsOfLine.get(3).equals("Regular")){
                todoList.add(new RegularItem(partsOfLine.get(0),partsOfLine.get(1), partsOfLine.get(2)));
            }
            if (partsOfLine.get(3).equals("Business")){
                todoList.add(new BusinessItem(partsOfLine.get(0),partsOfLine.get(1), partsOfLine.get(2)));
            }
        }
    }

    // EFFECTS: split ArraryList<String>
    private static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" : ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: input file name to exist
    // MODIFIES: this
    // EFFECTS: empty input file
    @Override
    public void emptyFile(String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(fileName,"UTF-8");
        writer.print("");
        writer.close();
    }

}
