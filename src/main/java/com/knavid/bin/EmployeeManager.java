package com.knavid.bin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.knavid.entity.Customer;
import com.knavid.entity.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class EmployeeManager implements DataManager<Employee> {

    private JSONArray employeeList = new JSONArray();

    public EmployeeManager() {
    }

    public void add(Employee emp) {
        List<Employee> employees = read();
        employees.add(emp);

        try (FileWriter file = new FileWriter("employees.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(new Gson().toJson(employees));
            file.flush();

        } catch (IOException e) {
            System.out.println("wrong");
        }

    }

    public List<Employee> read() {
        Type TYPE = new TypeToken<List<Employee>>() {}.getType();
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader("employees.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Employee> data = gson.fromJson(reader, TYPE); // contains the whole reviews list
        return data;

    }


    public Employee findById(int id){
        List<Employee> employeeList = read();

        for(Employee employee: employeeList){
            if(employee.getId() == id){
                return employee;
            }
        }

        return null;
    }

    public void update(Employee c,String key,Object up) {
        List<Employee> employees = read();

        if(key.equals("fullName")){
            c.setFullName((String) up);
        }
        if(key.equals("emailAddress")){
            c.setEmailAddress((String) up);
        }
        if(key.equals("role")){
            c.setRole((String) up);
        }
        if(key.equals("skype")){
            c.setSkype((String) up);
        }
        if(key.equals("phoneNumber")){
            c.setMobileNumber((String) up);
        }

        for (Employee employee : employees) {
            if (c.getId() == employee.getId()) {
                employees.remove(employee);
                employees.add(c);
                break;
            }
        }

        try (FileWriter file = new FileWriter("employees.json")) {
            // We can write any JSONArray or JSONObject instance to the file
            file.write(new Gson().toJson(employees));
            file.flush();

        } catch (IOException e) {
            System.out.println("wrong");
        }
    }


    public void remove(Employee c){
        List<Employee> employees = read();
        for (Employee employee : employees) {
            if (c.getId() == employee.getId()) {
                employees.remove(employee);
                break;
            }
        }
        try (FileWriter file = new FileWriter("employees.json")) {
            // We can write any JSONArray or JSONObject instance to the file
            file.write(new Gson().toJson(employees));
            file.flush();

        } catch (IOException e) {
            System.out.println("wrong");
        }





//        JSONObject cust = new JSONObject();
//        cust.put("employee", new Gson().toJson(c));
//
//        JSONParser jsonParser = new JSONParser();
//
//        try (FileReader reader = new FileReader("employees.json")) {
//            // Read JSON file
//            Object obj = jsonParser.parse(reader);
//
//            employeeList = (JSONArray) obj;
//
//            employeeList.remove(cust);
//
//            try (FileWriter file = new FileWriter("employees.json")) {
//                // We can write any JSONArray or JSONObject instance to the file
//                file.write(employeeList.toJSONString());
//                file.flush();
//
//            } catch (IOException e) {
//                System.out.println("wrong");
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

    }
}
