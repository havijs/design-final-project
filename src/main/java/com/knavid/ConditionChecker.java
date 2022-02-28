package com.knavid;

public interface ConditionChecker<T> {
   public boolean check(String condition, T t); 
}
