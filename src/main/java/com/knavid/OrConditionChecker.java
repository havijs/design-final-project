package com.knavid;

public class OrConditionChecker<T> implements ConditionChecker<T> {
    @Override
    public boolean check(String condition, T t) {
        AndConditionChecker<T> andConditionChecker = new AndConditionChecker<>();
        String[] orConditions = condition.split(" OR ");
        for (String orCondition : orConditions) {
           if(andConditionChecker.check(orCondition, t)) {
               return true;
           }
        }
        return false;
    }
    
}
