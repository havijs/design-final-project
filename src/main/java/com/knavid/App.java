package com.knavid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.knavid.bin.AppointmentManager;
import com.knavid.bin.CustomerManager;
import com.knavid.bin.EmployeeManager;
import com.knavid.entity.Appointment;
import com.knavid.entity.Customer;
import com.knavid.entity.Employee;

public class App 
{
    private static Scanner sc;

    private static CustomerManager customerManager = new CustomerManager();
    private static EmployeeManager employeeManager = new EmployeeManager();
    private static AppointmentManager appointmentManager = new AppointmentManager();
    private static Operator<Customer> customerOperator = new Operator<>(customerManager);
    private static Operator<Employee> staffOperator = new Operator<>(employeeManager);
    private static Operator<Appointment> appointmentOperator = new Operator<>(appointmentManager);

    public static void main( String[] args )
    {
        Action<Customer> action1 = new Call<>();
        Action<Customer> action2 = new Email<>();
        Action<Customer> action3 = new ChangeField<>("firstName", customerOperator, "Navid");
        UpdateEvent<Customer> createEvent = new UpdateEvent<>("age > 10 OR firstName = Navid", "firstName");
        CreateEvent<Customer> updateEvent = new CreateEvent<>("age < 10");
        RemoveEvent<Customer> removeEvent = new RemoveEvent<>("age < 10");
        createEvent.addAction(action1);
        createEvent.addAction(action2);
        updateEvent.addAction(action3);
        removeEvent.addAction(action3);
        customerOperator.addUpdateEvent(createEvent);
        customerOperator.addCreateEvent(updateEvent);
        customerOperator.addRemoveEvent(removeEvent);

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
        System.out.println("Choose operation: \n 1. Customer\n2. Employee\n3. Appointment");
        int choice = sc.nextInt();
        sc.nextLine();
        switch(choice) {
            case 1:
                printCustomers();
                break;
            case 2:
                printEmployees();
                break;
            case 3:
                printAppointments();
                break;
            default:
                System.out.println("Choice does not exist");
        }
    }

    private static void printAppointments() {
        List<Appointment> appointmentList = appointmentManager.read();

        for(int i = 0; i < appointmentList.size(); i++)
        {
            appointmentList.get(i).print();
        }
    }

    private static void printEmployees() {
        List<Employee> employeeList = employeeManager.read();

        for(int i = 0; i < employeeList.size(); i++)
        {
            employeeList.get(i).print();
        }
    }

    private static void printCustomers() {
        List<Customer> customerList = customerManager.read();

        for(int i = 0; i < customerList.size(); i++)
        {
            customerList.get(i).print();
        }

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
        System.out.println("Enter the value of field:");
        String value = sc.next();
        switch (choice) {
            case 1:
                fieldName = "firstName";
                break;
            case 2:
                fieldName = "lastName";
                break;
            case 3:
                fieldName = "email";
                break;
            case 4:
                fieldName = "age";
                break;
            case 5:
                fieldName = "phoneNumber";
                break;

        }

        customerOperator.update(c, fieldName, value);

    }

    private static void removeCustomerMenu() {
        System.out.println("Enter customer id");
        int id = sc.nextInt();
        sc.nextLine();
        customerManager.remove(customerManager.findById(id));
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
        Employee s = new Employee(fullName, emailAddress, role, skype, mobileNumber);
        staffOperator.create(s);
    }

    private static void updateStaffMenu() {
        String fieldName = "fullName";
        System.out.println("Enter Staff id");
        int id = sc.nextInt();
        sc.nextLine();
        Employee s = employeeManager.findById(id);
        System.out.println("Which field you want to update?\n1. Full name\n2. Email address\n3. Role \n4. Skype\n5. Mobile number");
        int choice = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the value of field");
        String value = sc.next();
        switch (choice) {
            case 1:
                fieldName = "fullName";
                break;
            case 2:
                fieldName = "emailAddress";
                break;
            case 3:
                fieldName = "role";
                break;
            case 4:
                fieldName = "skype";
                break;
            case 5:
                fieldName = "mobileNumber";
                break;
        }

        staffOperator.update(s, fieldName, value);

    }

    private static void removeStaffMenu() {
        System.out.println("Enter Staff id");
        int id = sc.nextInt();
        sc.nextLine();
        employeeManager.remove(employeeManager.findById(id));
    }




    private static void appointmentOperation(int choice) {
        switch(choice) {
            case 1:
                createAppointmentMenu();
                break;
            case 2:
                updateAppointmentMenu();
                break;
            case 3:
                removeAppointmentMenu();
                break;
            default:
                System.out.println("Choice does not exist");
        }
    }

    private static void createAppointmentMenu() {
        System.out.println("Enter Staff id:");
        int staffId = sc.nextInt();
        sc.nextLine();
        Employee employee = employeeManager.findById(staffId);
        System.out.println("Enter Customer id:");
        int customerId = sc.nextInt();
        sc.nextLine();
        Customer customer = customerManager.findById(customerId);
        Appointment a = new Appointment(new Date(), employee, customer);
        appointmentOperator.create(a);
    }

    private static void updateAppointmentMenu() {

    }

    private static void removeAppointmentMenu() {
        System.out.println("Enter Appointment id");
        int id = sc.nextInt();
        sc.nextLine();
        appointmentManager.remove(appointmentManager.findById(id));
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
                List<Action<Employee>> actions = new ArrayList<>();
                do {
                    Action<Employee> action = createActionMenu(entityChoice, staffOperator);
                    actions.add(action);
                    System.out.println("Insert 1 to add another action");
                    int addMore = sc.nextInt();
                    sc.nextLine();
                    if(addMore != 1) {
                        break;
                    }
                } while(true);
                CreateEvent<Employee> event = new CreateEvent<>(condition);
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
                List<Action<Employee>> actions = new ArrayList<>();
                do {
                    Action<Employee> action = createActionMenu(entityChoice, staffOperator);
                    actions.add(action);
                    System.out.println("Insert 1 to add another action");
                    int addMore = sc.nextInt();
                    sc.nextLine();
                    if(addMore != 1) {
                        break;
                    }
                } while(true);
                UpdateEvent<Employee> event = new UpdateEvent<>(condition, field);
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
                List<Action<Employee>> actions = new ArrayList<>();
                do {
                    Action<Employee> action = createActionMenu(entityChoice, staffOperator);
                    actions.add(action);
                    System.out.println("Insert 1 to add another action");
                    int addMore = sc.nextInt();
                    sc.nextLine();
                    if(addMore != 1) {
                        break;
                    }
                } while(true);
                RemoveEvent<Employee> event = new RemoveEvent<>(condition);
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
