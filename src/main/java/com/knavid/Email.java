package com.knavid;

public class Email<T> implements Action<T> {
    
    @Override
    public void execute(T object) {
        Emailable e = (Emailable) object;
        System.out.println("An email has sent to %s".formatted(e.getEmail()));        
    }
    
}
