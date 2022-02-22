package com.knavid.bin;

import java.util.List;

public interface DataManager<T> {
    List<T> findAll();
    T findById(int id);
    void add(T t);
    void remove(int id);
}
