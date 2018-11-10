package model;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<CustomObserver> observers;

    public Subject(){
        observers = new ArrayList<>();
    }

    public void addObserver(CustomObserver o){
        observers.add(o);
    }

    public void removeobserver(CustomObserver o){
        observers.remove(o);
    }

    public void notifyObservers(Item i){
        for(CustomObserver o: observers){
            o.update(i);
        }
    }

}
