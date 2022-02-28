package com.knavid;

import com.knavid.bin.DataManager;

import java.util.ArrayList;
import java.util.List;

public class Operator<T> {
    private List<UpdateEvent<T>> updateEvents;
    private List<CreateEvent<T>> createEvents;
    private List<RemoveEvent<T>> removeEvents;
    private DataManager<T> dataManager;


    public Operator(DataManager<T> dataManager) {
        this.dataManager = dataManager;
        this.updateEvents = new ArrayList<>();
        this.createEvents = new ArrayList<>();
        this.removeEvents = new ArrayList<>();
    }

    public void create(T t) {
        for (CreateEvent<T> event : createEvents) {
            if(event.checkCondition(t)){
                for (Action<T> action : event.getActions()) {
                    action.execute(t);
                }
            }
        }
        dataManager.add(t);

    }

    public void update(T t, String fieldName, Object value) {
        for (UpdateEvent<T> event : updateEvents) {
            if(event.getField().equals(fieldName) && event.checkCondition(t)) {
                for (Action<T> action : event.getActions()) {
                    action.execute(t);
                }
            }
        }
        dataManager.update(t,fieldName,value);


    }

    public void remove(T t) {
        for (RemoveEvent<T> event : removeEvents) {
            if(event.checkCondition(t)){
                for (Action<T> action : event.getActions()) {
                    action.execute(t);
                }
            }
        }
        dataManager.remove(t);

    }

    public void addUpdateEvent(UpdateEvent<T> event) {
        this.updateEvents.add(event);
    }

    public void addCreateEvent(CreateEvent<T> event) {
        this.createEvents.add(event);
    }

    public void addRemoveEvent(RemoveEvent<T> event) {
        this.removeEvents.add(event);
    }
}
