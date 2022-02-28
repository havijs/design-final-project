package com.knavid.bin;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.knavid.entity.Appointment;
import com.knavid.entity.Customer;
import com.knavid.entity.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AppointmentManager implements DataManager<Appointment> {
    private JSONArray appointmentList = new JSONArray();

    public AppointmentManager() {
    }

    public void add(Appointment appointment) {
        List<Appointment> appointments = read();
        appointments.add(appointment);

        try (FileWriter file = new FileWriter("appointments.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(new Gson().toJson(appointments));
            file.flush();

        } catch (IOException e) {
            System.out.println("wrong");
        }

    }

    public List<Appointment> read() {
        Type TYPE = new TypeToken<List<Appointment>>() {}.getType();
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader("appointments.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Appointment> data = gson.fromJson(reader, TYPE); // contains the whole reviews list
        return data;

    }

    public Appointment findById(int id){
        List<Appointment> appointmentList = read();

        for(Appointment appointment: appointmentList){
            if(appointment.getId() == id){
                return appointment;
            }
        }

        return null;
    }

    public void update(Appointment c,String key,Object up) {

        JSONObject cust = new JSONObject();
        cust.put("employee", new Gson().toJson(c));

        if(key.equals("id")){
            c.setId((Integer) up);
        }
        if(key.equals("date")){
            c.setDate((Date) up);
        }
        if(key.equals("employee")){
            c.setEmployee((Employee) up);
        }
        if(key.equals("customer")){
            c.setCustomer((Customer) up);
        }

        JSONObject cust1 = new JSONObject();
        cust1.put("customer", new Gson().toJson(c));

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("appointme.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            appointmentList = (JSONArray) obj;

            appointmentList.remove(cust);

            appointmentList.add(cust1);

            try (FileWriter file = new FileWriter("appointme.json")) {
                //We can write any JSONArray or JSONObject instance to the file
                file.write(appointmentList.toJSONString());
                file.flush();

            } catch (IOException e) {
                System.out.println("wrong");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void remove(Appointment c){

        JSONObject cust = new JSONObject();
        cust.put("employee", new Gson().toJson(c));

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("appointme.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            appointmentList = (JSONArray) obj;

            appointmentList.remove(cust);

            try (FileWriter file = new FileWriter("appointme.json")) {
                //We can write any JSONArray or JSONObject instance to the file
                file.write(appointmentList.toJSONString());
                file.flush();

            } catch (IOException e) {
                System.out.println("wrong");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
