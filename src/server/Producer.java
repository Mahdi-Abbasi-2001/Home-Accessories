package server;

import java.util.ArrayList;

public class Producer {
    private String name;
    private String username;
    private String password;
    private final ArrayList<Product> products = new ArrayList<>();
    public Producer(String name, String username, String password){
        this.name = name;
        this.username = username;
        this.password = password;
        Users.producers.add(this);
        Users.manager.getProducers().add(this);
    }

    public ArrayList<Product> getProducts(){
        return products;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
