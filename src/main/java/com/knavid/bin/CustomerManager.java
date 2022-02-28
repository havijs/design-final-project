package com.knavid.bin;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.knavid.entity.Customer;
import org.json.simple.JSONArray;

public class CustomerManager implements DataManager<Customer> {

    private JSONArray customerList = new JSONArray();

    public CustomerManager() {
    }

    public void add(Customer cus) {
        List<Customer> customers = read();
        customers.add(cus);

        try (FileWriter file = new FileWriter("customers.json")) {
            // We can write any JSONArray or JSONObject instance to the file
            file.write(new Gson().toJson(customers));
            file.flush();

        } catch (IOException e) {
            System.out.println("wrong");
        }

    }

    public List<Customer> read() {

        Type TYPE = new TypeToken<List<Customer>>() {
        }.getType();
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

    public Customer findById(int id) {
        List<Customer> customerList = read();

        for (Customer customer : customerList) {
            if (customer.getId() == id) {
                return customer;
            }
        }

        return null;
    }

    public void update(Customer c, String key, Object up) {
        List<Customer> customers = read();

        if (key.equals("id")) {
            c.setId((Integer) up);
        }
        if (key.equals("firstName")) {
            c.setFirstName((String) up);
        }
        if (key.equals("lastName")) {
            c.setLastName((String) up);
        }
        if (key.equals("email")) {
            c.setEmail((String) up);
        }
        if (key.equals("age")) {
            c.setAge((Integer) up);
        }
        if (key.equals("phoneNumber")) {
            c.setPhoneNumber((String) up);
        }
        for (Customer customer : customers) {
            if (c.getId() == customer.getId()) {
                customers.remove(customer);
                customers.add(c);
                break;
            }
        }

        try (FileWriter file = new FileWriter("customers.json")) {
            // We can write any JSONArray or JSONObject instance to the file
            file.write(new Gson().toJson(customers));
            file.flush();

        } catch (IOException e) {
            System.out.println("wrong");
        }
    }

    public void remove(Customer c) {
        List<Customer> customers = read();

        for (Customer customer : customers) {
            if (customer.getId() == c.getId()) {
                customers.remove(customer);
                break;
            }
        }

        try (FileWriter file = new FileWriter("customers.json")) {
            // We can write any JSONArray or JSONObject instance to the file
            file.write(new Gson().toJson(customers));
            file.flush();

        } catch (IOException e) {
            System.out.println("wrong");
        }

    }
}
