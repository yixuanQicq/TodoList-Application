package model;

public class TodoListCustomObserver implements CustomObserver {
    private int itemCount;

    public TodoListCustomObserver(){
        itemCount = 0;
    }

    @Override
    public void update(Item i) {
        itemCount++;
        System.out.println("You have added " + i.getName() + " to the TodoList");
        if(itemCount%5 == 0){
            System.out.println("For this access, you have " + itemCount + " items undo in the list!");
        }

    }


}
