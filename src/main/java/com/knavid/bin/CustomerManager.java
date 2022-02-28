package com.knavid.bin;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.knavid.entity.Customer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CustomerManager implements DataManager<Customer> {

    private JSONArray customerList = new JSONArray();

    public CustomerManager() {
    }

    public void add(Customer cus) {
        JSONObject cus1 = new JSONObject();
        cus1.put("customer", new Gson().toJson(cus));

        customerList.add(cus1);

        try (FileWriter file = new FileWriter("customers.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(customerList.toJSONString());
            file.flush();

        } catch (IOException e) {
            System.out.println("wrong");
        }

    }

    public List<Customer> read() {

        Type TYPE = new TypeToken<List<Customer>>() {}.getType();
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader("customers.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Customer> data = gson.fromJson(reader, TYPE); // contains the whole reviews list
        return data;
    }


    public Customer findById(int id){
        List<Customer> customerList = read();

        for(Customer customer: customerList){
            if(customer.getId() == id){
                return customer;
            }
        }

        return null;
    }

    public void update(Customer c,String key,Object up) {

        JSONObject cust = new JSONObject();
        cust.put("customer", new Gson().toJson(c));

        if(key.equals("id")){
            c.setId((Integer) up);
        }
        if(key.equals("firstName")){
            c.setFirstName((String) up);
        }
        if(key.equals("lastName")){
            c.setLastName((String) up);
        }
        if(key.equals("email")){
            c.setEmail((String) up);
        }
        if(key.equals("age")){
            c.setAge((Integer) up);
        }
        if(key.equals("phoneNumber")){
            c.setPhoneNumber((String) up);
        }

        JSONObject cust1 = new JSONObject();
        cust1.put("customer", new Gson().toJson(c));

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("customers.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            customerList = (JSONArray) obj;

            customerList.remove(cust);

            customerList.add(cust1);

            try (FileWriter file = new FileWriter("customers.json")) {
                //We can write any JSONArray or JSONObject instance to the file
                file.write(customerList.toJSONString());
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


    public void remove(Customer c){

        JSONObject cust = new JSONObject();
        cust.put("customer", new Gson().toJson(c));

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("customers.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            customerList = (JSONArray) obj;

            customerList.remove(cust);

            try (FileWriter file = new FileWriter("customers.json")) {
                //We can write any JSONArray or JSONObject instance to the file
                file.write(customerList.toJSONString());
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
