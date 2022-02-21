package com.knavid;

public class UpdateEvent<T> extends Event<T> {

    private String field;

    public UpdateEvent(String condition, String field) {
        super(condition);
        this.field = field;
    }

    @Override
    public boolean checkCondition(T t) {
        return true;
    }

    public String getField() {
        return this.field;
    }
}
