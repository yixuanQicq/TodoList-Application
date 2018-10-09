package model;

public class UrgentItem extends Item {
    public UrgentItem(String name, String dueDate) {
        super(name, dueDate);
    }

    public UrgentItem(String name, String status, String dueDate) {
        super(name, status, dueDate);
    }

    @Override
    public String getItemType() {
        return "Urgent";
    }
}
