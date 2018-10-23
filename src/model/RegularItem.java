package model;


public class RegularItem extends Item{
    public RegularItem(String name, String dueDate) {
        super(name, dueDate);
    }

    RegularItem(String name, String status, String dueDate) {
        super(name, status, dueDate);
    }

    @Override
    public String getItemType() {
        return "Regular";
    }
}
