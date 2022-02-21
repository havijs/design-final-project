package com.knavid;

public class Call implements Action {

    private String number;

    public Call(String number) {
        this.number = number;
    }

    @Override
    public void execute() {
        System.out.print("Number %s has called".formatted(this.number));        
    }
    
}
