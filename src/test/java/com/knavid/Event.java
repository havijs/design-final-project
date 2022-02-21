package com.knavid;

import java.util.ArrayList;
import java.util.List;

public abstract class Event<T> {
    private String condition;
    private List<Action> actions;

    public Event(String condition) {
        this.condition = condition;
        this.actions = new ArrayList<>();
    }

    public void addAction(Action action) {
        this.actions.add(action);
    }

    public List<Action> getActions() {
        return this.actions;
    }

    public boolean checkCondition(T t) {
        return true;
    }
}
