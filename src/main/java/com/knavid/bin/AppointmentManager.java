package com.knavid.bin;

<<<<<<< HEAD
public class AppointmentManager implements DataManager {
=======
import java.util.List;

import com.knavid.entity.Appointment;

public class AppointmentManager implements DataManager<Appointment> {

    @Override
    public List<Appointment> findAll() {
        return null;
    }

    @Override
    public Appointment findById(int id) {
        return null;
    }

    @Override
    public void add(Appointment t) {
        
    }

    @Override
    public void remove(int id) {
        
    }
>>>>>>> origin/main
}
