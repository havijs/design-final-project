package com.knavid;

public class Email implements Action {
    
    private String email;

    public Email(String email) {
        this.email = email;
    }

    @Override
    public void execute() {
        System.out.print("An email has sent to %s".formatted(this.email));        
    }
    
}
