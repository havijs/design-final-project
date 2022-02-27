package com.knavid;

import java.lang.reflect.Field;
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
        String[] orConditions = condition.split(" OR ");
        for (String orCondition : orConditions) {
           if(checkAndCondition(t, orCondition)) {
               return true;
           }
        }
        return false;
    }

    private boolean checkAndCondition(T t, String condition) {
        String[] andConditions = condition.split(" AND ");
        for (String andCondition : andConditions) {
           if(!checkAtomicCondition(t, andCondition)) {
               return false;
           }
        }
        return true;
    }

    private boolean checkAtomicCondition(T t, String condition) {
        String[] conditionParts = condition.split(" ");
        String fieldName = conditionParts[0];
        String operator = conditionParts[1];
        String expectedValue = conditionParts[2];
        Field field;
        try {
            field = t.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(t);
            if(operator.equals("=")) {
                if(field.getType().isAssignableFrom(Integer.class)) {
                    int convertedExpectedValue = Integer.parseInt(expectedValue);
                    int convertedValue = (int) value;
                    return convertedExpectedValue == convertedValue;
                }
                if(field.getType().isAssignableFrom(String.class)) {
                    String convertedValue = (String) value;
                    return convertedValue.equals(expectedValue);
                }
            } else if(operator.equals(">")) {
                if(field.getType().isAssignableFrom(int.class)) {
                    int convertedExpectedValue = Integer.parseInt(expectedValue);
                    int convertedValue = (int) value;
                    return convertedExpectedValue < convertedValue;
                }

            } else if(operator.equals("<")) {
                if(field.getType().isAssignableFrom(Integer.class)) {
                    int convertedExpectedValue = Integer.parseInt(expectedValue);
                    int convertedValue = (int) value;
                    return convertedExpectedValue > convertedValue;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
