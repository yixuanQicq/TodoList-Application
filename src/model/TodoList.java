package model;

import model.Exception.DateIncorrectFormatException;
import model.Exception.TooManyThingsException;
import model.Exception.TooManyUrgentItemException;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TodoList implements Loadable, Saveable {
    private static final int URGENTITEMLIMIT = 5;
    private static final int ITEMLIMIT = 25;

    private ArrayList<Item> todoList;
    private Date currentDate = new Date();
    private Map<User,Password> userSystem;


    public TodoList (){
        todoList = new ArrayList<>();
        userSystem = new HashMap<>();
        defaultUser();
    }


    // REQUIRES: user input according to required format
    // MODIFIES: this
    // EFFECTS: add a new item to the list, throws ParseException if date input is in wrong format
    public void addItem(Item i) throws TooManyUrgentItemException, TooManyThingsException {
        int urgentCount = countUrgentItem();
        if(i.getItemType().equals("Urgent") && urgentCount >= URGENTITEMLIMIT){
            throw new TooManyUrgentItemException("Too Many Urgent Items in the List.");
        }
        if(todoList.size() >= ITEMLIMIT){
            throw new TooManyThingsException("Too Many Item in the List");
        }
        todoList.add(i);
    }

    // EFFECTS: count the number of urgent item in the todolist
    private int countUrgentItem() {
        int counter = 0;
        for (Item i: todoList){
            if(i.getItemType().equals("Urgent")){
                counter+=1;
            }
        }
        return counter;
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
    //          throw DateIncorrectFormatException if catch ParseException
    public void checkOverDue() throws DateIncorrectFormatException {
        for (Item i : todoList) {
            DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
            Date date;
            try {
                date = format.parse(i.getDueDate());
                if (date.before(currentDate)) {
                    if (!(i.getStatus().equals("Done"))) {
                        i.setStatus("Overdue");
                    }
                }
                if (i.getStatus().equals("Overdue") && date.after(currentDate)){
                    i.setStatus("In-progress");
                }
            } catch (ParseException e) {
                throw new DateIncorrectFormatException();
            }
        }
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
    public void saveItem(String fileName) throws IOException {
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

    @Override
    public void saveUserSystem(String fileName) throws IOException {
        List<String> newLines = new ArrayList<>();
        PrintWriter writer = new PrintWriter(fileName,"UTF-8");
        Set<Map.Entry<User, Password>> entries = userSystem.entrySet();
        for (Map.Entry<User, Password> entry : entries) {
            newLines.add(entry.getKey().getUserName() + " : " + Integer.toString(entry.getValue().getPw()));
        }
        for (String line : newLines){
            writer.println(line);
        }
        writer.close();
    }

    // REQUIRES: input file name to exist
    // EFFECTS: print file items into todolist
    @Override
    public void loadItem(String fileName) throws IOException {
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

    @Override
    public void loadUserSystem(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        for (String line: lines){
            ArrayList<String> partsOfLine = splitOnSpace(line);
            Password password = new Password(Integer.parseInt(partsOfLine.get(1)));
            User user = new User(partsOfLine.get(0), password);
            userSystem.put(user, password);
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

    // EFFECTS: check if user have access to the todolist, return false if access denied
    public boolean accessVerification(int passWordEntered, String nameEntered){
        Password pwEntered = new Password(passWordEntered);
        User userEntered = new User(nameEntered, pwEntered);
        Set<User> users = userSystem.keySet();
        for(User u: users){
            if (u.equals(userEntered)){
                return true;
            }
        }
        System.out.println("Access denied: incorrect password or invalid username");
        return false;
    }

    // MODIFIES: User
    // EFFECTS: set user password to passwordentered
    public void resetPasswords(User u, int passwordentered) {
        Password pw  = new Password(passwordentered);
        userSystem.remove(u);
        u.setPasswords(pw);
        userSystem.put(u,pw);
    }

    // MODIFIES: this
    // EFFECTS: add a new user to user system
    public void addUser(User u){
        userSystem.put(u, u.getPasswords());
    }

    public void addUser(String userName, int password){
        Password passwordEntered = new Password(password);
        User user = new User(userName, passwordEntered);
        userSystem.put(user, user.getPasswords());
    }

    // MODIFIES: this
    // EFFECTS: add a default user admin to the user system
    private void defaultUser(){
        User admin = new User("admin");
        addUser(admin);
    }

    public Map<User, Password> getUserSystem() {
        return userSystem;
    }
}
