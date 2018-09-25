package model;

import java.util.Date;

public class Item {

    private String name;
    private String  status;
    private Date dueDate;

    public Item (String name, Date dueDate){
        this.name = name;
        this.dueDate = dueDate;
        status = "in progress";
    }

    // EFFECTS: return the name of the item
    public String getName() {
        return name;
    }

    // EFFECTS: return dueDate of the item
    public Date getDueDate() {
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
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
