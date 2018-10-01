package model;

import java.util.Date;

public class Item {

    private String name;
    private String  status;
    private String dueDate;

    public Item (String name, String dueDate){
        this.name = name;
        this.dueDate = dueDate;
        status = "In-progress";
    }

    public Item (String name, String status, String dueDate){
        this.name = name;
        this.status = status;
        this.dueDate = dueDate;
    }

    // EFFECTS: return the name of the item
    public String getName() {
        return name;
    }

    // EFFECTS: return dueDate of the item
    public String getDueDate() {
        return dueDate;
    }

    // EFFECTS: return status of the item
    public String getStatus() {
        return status;
    }


    // MODIFIES: this
    // EFFECTS: change the name of the item
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: change the status of the item
    public void setStatus(String status) {
        this.status = status;
    }

    // MODIFIES: this
    // EFFECTS: change the status of the item
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
