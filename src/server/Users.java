package server;

import java.util.ArrayList;

public class Users {
    public static ArrayList<Customer> customers = new ArrayList<>();
    public static ArrayList<Producer> producers = new ArrayList<>();
    public static Manager manager;

    public static Customer customerExist(String username , String password){
        for(Customer customer:customers)
            if(customer.getUsername().equals(username) && customer.getPassword().equals(password))
                return customer;
        return null;
    }

    public static Producer producerExist(String username , String password){
        for(Producer producer:producers)
            if(producer.getUsername().equals(username) && producer.getPassword().equals(password))
                return producer;
        return null;
    }

    public static boolean signupCheck(String name, String username, String password){
        if((!(name.isEmpty() || username.isEmpty() || password.isEmpty()))){
            for (Customer customer: customers)
                if (customer.getUsername().equals(username))
                    return false;
            for (Producer producer: producers)
                if (producer.getUsername().equals(username))
                    return false;
            return !manager.getUsername().equals(username);
        }
        else
            return false;
    }
}
