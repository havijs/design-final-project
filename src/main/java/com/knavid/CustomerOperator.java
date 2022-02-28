package com.knavid;

import com.knavid.bin.DataManager;
import com.knavid.entity.Customer;

public class CustomerOperator extends Operator<Customer> {

    public CustomerOperator(DataManager<Customer> dataManager) {
        super(dataManager);
    }
    
}
