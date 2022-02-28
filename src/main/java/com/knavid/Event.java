package com.knavid;

import java.util.ArrayList;
import java.util.List;

public abstract class Event<T> {
    private String condition;
    private List<Action<T>> actions;

    public Event(String condition) {
        this.condition = condition;
        this.actions = new ArrayList<>();
    }

    public void addAction(Action<T> action) {
        this.actions.add(action);
    }
    public void addActions(List<Action<T>> actions) {
        for (Action<T> action : actions) {
            this.actions.add(action);
        }
    }

    public List<Action<T>> getActions() {
        return this.actions;
    }

    public boolean checkCondition(T t) {
        ConditionChecker<T> conditionChecker = new OrConditionChecker<>();
        return conditionChecker.check(condition, t);
    }
}
