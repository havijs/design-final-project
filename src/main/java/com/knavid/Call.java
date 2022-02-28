package com.knavid;

public class Call<T> implements Action<T> {

    @Override
    public void execute(T object) {
        Phonable p = (Phonable) object;
        System.out.println("Number %s has called".formatted(p.getPhoneNumber()));
    }
    
}
