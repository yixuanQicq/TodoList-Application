package model;

public class BusinessItem extends Item{

    public BusinessItem(String name, String dueDate) {
        super(name, dueDate);
    }

    public BusinessItem(String name, String status, String dueDate) {
        super(name, status, dueDate);
    }

    @Override
    public String getItemType() {
        return "Business";
    }
}
