package com.knavid;

import com.knavid.bin.DataManager;
import com.knavid.entity.Appointment;

public class AppointmentOperator extends Operator<Appointment> {

    public AppointmentOperator(DataManager<Appointment> dataManager) {
        super(dataManager);
    }
    
}
