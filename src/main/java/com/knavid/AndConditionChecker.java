package com.knavid;

public class AndConditionChecker<T> implements ConditionChecker<T>{

    @Override
    public boolean check(String condition, T t) {
        AtomicConditionChecker<T> atomicConditionChecker = new AtomicConditionChecker<>();
        String[] andConditions = condition.split(" AND ");
        for (String andCondition : andConditions) {
           if(!atomicConditionChecker.check(andCondition, t)) {
               return false;
           }
        }
        return true;
    }
    
}
