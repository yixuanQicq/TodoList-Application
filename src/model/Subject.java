package model;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers;

    public Subject(){
        observers = new ArrayList<>();
    }

    public void addObserver(Observer o){
        observers.add(o);
    }

    public void removeobserver(Observer o){
        observers.remove(o);
    }

    public void notifyObservers(Item i){
        for(Observer o: observers){
            o.update(i);
        }
    }

}
