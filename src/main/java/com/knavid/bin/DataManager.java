package com.knavid.bin;

import com.knavid.entity.Customer;
import com.knavid.entity.Employee;

import java.util.List;

public interface DataManager<T> {
    public List<T> read();
    public void add(T t);
    public void update(T t, String key, Object object);
    public void remove(T t);
}
