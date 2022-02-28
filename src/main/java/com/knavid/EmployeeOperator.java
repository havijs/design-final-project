package com.knavid;

import com.knavid.bin.DataManager;
import com.knavid.entity.Employee;

public class EmployeeOperator extends Operator<Employee> {

    public EmployeeOperator(DataManager<Employee> dataManager) {
        super(dataManager);
    }
    
}
