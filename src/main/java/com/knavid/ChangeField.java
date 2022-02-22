package com.knavid;

public class ChangeField<T> implements Action<T> {

    private String field;
    private Operator<T> operator;
    private Object value;

    public ChangeField(String field, Operator<T> operator, Object value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    @Override
    public void execute(T t) {
       operator.update(t, field); 
    }
    
}
