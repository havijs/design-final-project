package com.knavid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.knavid.bin.AppointmentManager;
import com.knavid.bin.CustomerManager;
import com.knavid.bin.StaffManger;
import com.knavid.entity.Appointment;
import com.knavid.entity.Customer;
import com.knavid.entity.Staff;

public class App 
{
    private static Scanner sc;
    private static Operator<Customer> customerOperator = new Operator<>();
    private static Operator<Staff> staffOperator = new Operator<>();
    private static Operator<Appointment> appointmentOperator = new Operator<>();
    private static CustomerManager customerManager = new CustomerManager();
    private static StaffManger staffManger = new StaffManger();
    private static AppointmentManager appointmentManager = new AppointmentManager();

    public static void main( String[] args )
    {
        sc = new Scanner(System.in);
        do {
            System.out.println("1. List entities\n2. Operations\n3. Create event");
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice) {
                case 1:
                    listEntities();
                    break;
                case 2:
                    operations();
                    break;
                case 3:
                    createEvent();
                    break;
                default:
                    System.out.println("Choice does not exist");
            }
        } while(true);
    }

    private static void listEntities() {

    }

    private static void operations() {
        System.out.println("Choose operation:\n1. Create\n2. Update\n3. Remove");
        int choice = sc.nextInt();
        sc.nextLine();
        System.out.println("Choose entity:\n1. Customer\n2. Staff\n3. Appointment");
        int entityChoice = sc.nextInt();
        sc.nextLine();
        switch(entityChoice) {
            case 1:
                customerOperation(choice);
                break;
            case 2:
                staffOperation(choice);
                break;
            case 3:
                appointmentOperation(choice);
                break;
            default:
                System.out.println("Choice does not exist");
        }
    }

    private static void customerOperation(int choice) {
        switch(choice) {
            case 1:
                createCustomerMenu();
                break;
            case 2:
                updateCustomerMenu();
                break;
            case 3:
                removeCustomerMenu();
                break;
            default:
                System.out.println("Choice does not exist");
        }
    }

    private static void createCustomerMenu() {
        System.out.println("Customer first name:");
        String firstName = sc.next();
        System.out.println("Customer last name:");
        String lastName = sc.next();
        System.out.println("Customer email:");
        String email = sc.next();
        System.out.println("Customer age:");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.println("Customer phone number:");
        String phoneNumber = sc.next();
        Customer c = new Customer(firstName, lastName, email, age, new Date(), phoneNumber);

        customerOperator.create(c);
    }

    private static void updateCustomerMenu() {
        String fieldName = "firstName";
        System.out.println("Enter customer id");
        int customerId = sc.nextInt();
        sc.nextLine();
        Customer c = customerManager.findById(customerId);
        System.out.println("Which field you want to update?\n1. First name\n2. Last name\n3. Email \n4. Age\n5. Phone number");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                fieldName = "firstName";
                String firstName = sc.next();
                c.setFirstName(firstName);
                break;
            case 2:
                fieldName = "lastName";
                String lastName = sc.next();
                c.setLastName(lastName);
                break;
            case 3:
                fieldName = "email";
                String email = sc.next();
                c.setEmail(email);
                break;
            case 4:
                fieldName = "age";
                int age = sc.nextInt();
                sc.nextLine();
                c.setAge(age);
                break;
            case 5:
                fieldName = "phoneNumber";
                String phoneNumber = sc.next();
                sc.nextLine();
                c.setPhoneNumber(phoneNumber);
                break;

        }

        customerOperator.update(c, fieldName);

    }

    private static void removeCustomerMenu() {
        System.out.println("Enter customer id");
        int id = sc.nextInt();
        sc.nextLine();
        customerManager.remove(id);
    }

    private static void staffOperation(int choice) {
        switch(choice) {
            case 1:
                createStaffMenu();
                break;
            case 2:
                updateStaffMenu();
                break;
            case 3:
                removeStaffMenu();
                break;
            default:
                System.out.println("Choice does not exist");
        }
    }

    private static void createStaffMenu() {
        System.out.println("Staff full name:");
        String fullName = sc.next();
        System.out.println("Staff email address:");
        String emailAddress = sc.next();
        System.out.println("Staff role:");
        String role = sc.next();
        System.out.println("Staff skype:");
        String skype = sc.next();
        System.out.println("Staff mobile number:");
        String mobileNumber = sc.next();
        Staff s = new Staff(fullName, emailAddress, role, skype, mobileNumber);
        staffOperator.create(s);
    }

    private static void updateStaffMenu() {
        String fieldName = "fullName";
        System.out.println("Enter Staff id");
        int id = sc.nextInt();
        sc.nextLine();
        Staff s = staffManger.findById(id);
        System.out.println("Which field you want to update?\n1. Full name\n2. Email address\n3. Role \n4. Skype\n5. Mobile number");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                fieldName = "fullName";
                String fullName = sc.next();
                s.setFullName(fullName);
                break;
            case 2:
                fieldName = "emailAddress";
                String emailAddress = sc.next();
                s.setEmailAddress(emailAddress);
                break;
            case 3:
                fieldName = "role";
                String role = sc.next();
                s.setRole(role);
                break;
            case 4:
                fieldName = "skype";
                String skype = sc.next();
                s.setSkype(skype);
                break;
            case 5:
                fieldName = "mobileNumber";
                String mobileNumber = sc.next();
                s.setMobileNumber(mobileNumber);
                break;

        }

        staffOperator.update(s, fieldName);

    }

    private static void removeStaffMenu() {
        System.out.println("Enter Staff id");
        int id = sc.nextInt();
        sc.nextLine();
        staffManger.remove(id);
    }




    private static void appointmentOperation(int choice) {
        switch(choice) {
            case 1:
                createAppointmentfMenu();
                break;
            case 2:
                updateAppointmentfMenu();
                break;
            case 3:
                removeAppointmentfMenu();
                break;
            default:
                System.out.println("Choice does not exist");
        }
    }

    private static void createAppointmentfMenu() {
        System.out.println("Enter Staff id:");
        int staffId = sc.nextInt();
        sc.nextLine();
        Staff staff = staffManger.findById(staffId);
        System.out.println("Enter Customer id:");
        int customerId = sc.nextInt();
        sc.nextLine();
        Customer customer = customerManager.findById(customerId);
        Appointment a = new Appointment(new Date(), staff, customer);
        appointmentOperator.create(a);
    }

    private static void updateAppointmentfMenu() {

    }

    private static void removeAppointmentfMenu() {
        System.out.println("Enter Appointment id");
        int id = sc.nextInt();
        sc.nextLine();
        appointmentManager.remove(id);
    }

    private static void createEvent() {
        System.out.println("Enter event entity:\n1. Customer\n2. Staff\n3. Appointment");
        int entityChoice = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter operation:\n1. Create\n2. Update\n3. Remove");
        int operationChoice = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter condition:");
        String condition = sc.nextLine();

        if(operationChoice == 1) {
            if(entityChoice == 1) {
                List<Action<Customer>> actions = new ArrayList<>();
                do {
                    Action<Customer> action = createActionMenu(entityChoice, customerOperator);
                    actions.add(action);
                    System.out.println("Insert 1 to add another action");
                    int addMore = sc.nextInt();
                    sc.nextLine();
                    if(addMore != 1) {
                        break;
                    }
                } while(true);
                CreateEvent<Customer> event = new CreateEvent<>(condition);
                event.addActions(actions);
                customerOperator.addCreateEvent(event);
            }
            if(entityChoice == 2) {
                List<Action<Staff>> actions = new ArrayList<>();
                do {
                    Action<Staff> action = createActionMenu(entityChoice, staffOperator);
                    actions.add(action);
                    System.out.println("Insert 1 to add another action");
                    int addMore = sc.nextInt();
                    sc.nextLine();
                    if(addMore != 1) {
                        break;
                    }
                } while(true);
                CreateEvent<Staff> event = new CreateEvent<>(condition);
                event.addActions(actions);
                staffOperator.addCreateEvent(event);
            }
            if(entityChoice == 3) {
                List<Action<Appointment>> actions = new ArrayList<>();
                do {
                    Action<Appointment> action = createActionMenu(entityChoice, appointmentOperator);
                    actions.add(action);
                    System.out.println("Insert 1 to add another action");
                    int addMore = sc.nextInt();
                    sc.nextLine();
                    if(addMore != 1) {
                        break;
                    }
                } while(true);
                CreateEvent<Appointment> event = new CreateEvent<>(condition);
                event.addActions(actions);
                appointmentOperator.addCreateEvent(event);
            }
        }
        if(operationChoice == 2) {
            System.out.println("Enter event field");
            String field = sc.next();
            if(entityChoice == 1) {
                List<Action<Customer>> actions = new ArrayList<>();
                do {
                    Action<Customer> action = createActionMenu(entityChoice, customerOperator);
                    actions.add(action);
                    System.out.println("Insert 1 to add another action");
                    int addMore = sc.nextInt();
                    sc.nextLine();
                    if(addMore != 1) {
                        break;
                    }
                } while(true);
                UpdateEvent<Customer> event = new UpdateEvent<>(condition, field);
                event.addActions(actions);
                customerOperator.addUpdateEvent(event);
            }
            if(entityChoice == 2) {
                List<Action<Staff>> actions = new ArrayList<>();
                do {
                    Action<Staff> action = createActionMenu(entityChoice, staffOperator);
                    actions.add(action);
                    System.out.println("Insert 1 to add another action");
                    int addMore = sc.nextInt();
                    sc.nextLine();
                    if(addMore != 1) {
                        break;
                    }
                } while(true);
                UpdateEvent<Staff> event = new UpdateEvent<>(condition, field);
                event.addActions(actions);
                staffOperator.addUpdateEvent(event);
            }
            if(entityChoice == 3) {
                List<Action<Appointment>> actions = new ArrayList<>();
                do {
                    Action<Appointment> action = createActionMenu(entityChoice, appointmentOperator);
                    actions.add(action);
                    System.out.println("Insert 1 to add another action");
                    int addMore = sc.nextInt();
                    sc.nextLine();
                    if(addMore != 1) {
                        break;
                    }
                } while(true);
                UpdateEvent<Appointment> event = new UpdateEvent<>(condition, field);
                event.addActions(actions);
                appointmentOperator.addUpdateEvent(event);
            }
        }
        if(operationChoice == 3) {
            if(entityChoice == 1) {
                List<Action<Customer>> actions = new ArrayList<>();
                do {
                    Action<Customer> action = createActionMenu(entityChoice, customerOperator);
                    actions.add(action);
                    System.out.println("Insert 1 to add another action");
                    int addMore = sc.nextInt();
                    sc.nextLine();
                    if(addMore != 1) {
                        break;
                    }
                } while(true);
                RemoveEvent<Customer> event = new RemoveEvent<>(condition);
                event.addActions(actions);
                customerOperator.addRemoveEvent(event);
            }
            if(entityChoice == 2) {
                List<Action<Staff>> actions = new ArrayList<>();
                do {
                    Action<Staff> action = createActionMenu(entityChoice, staffOperator);
                    actions.add(action);
                    System.out.println("Insert 1 to add another action");
                    int addMore = sc.nextInt();
                    sc.nextLine();
                    if(addMore != 1) {
                        break;
                    }
                } while(true);
                RemoveEvent<Staff> event = new RemoveEvent<>(condition);
                event.addActions(actions);
                staffOperator.addRemoveEvent(event);
            }
        }

    }

    private static <T> Action<T> createActionMenu(int entityChoice, Operator<T> operator) {
        System.out.println("Enter action type:\n1. Call\n2. Email\n3. Change a field");
        int choice = sc.nextInt();
        sc.nextLine();
        switch(choice) {
            case 1:
                return new Call<T>();
            case 2:
                return new Email<T>();
            case 3:
                System.out.println("Enter field name:");
                String field = sc.next();
                System.out.println("Enter field value:");
                String value = sc.next();
                return new ChangeField<T>(field, operator, value);
            default:
                return null;
        }
    }
}
