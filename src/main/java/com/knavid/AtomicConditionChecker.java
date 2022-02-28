package com.knavid;

import java.lang.reflect.Field;

public class AtomicConditionChecker<T> implements ConditionChecker<T> {

    @Override
    public boolean check(String condition, T t) {
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
                if(field.getType().isAssignableFrom(int.class)) {
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
