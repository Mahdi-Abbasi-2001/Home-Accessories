package server;

import java.util.ArrayList;

public class Manager {
    private final ArrayList<Producer> producers = new ArrayList<>();
    private final ArrayList<Product> products = new ArrayList<>();
    private String username;
    private String password;
    public Manager(String username, String password){
        this.username = username;
        this.password = password;
        Customer.manager = this;
        Users.manager = this;
    }

    public void setDiscount(int percent){
        for(Product product:products)
            product.setPrice(product.getPrice()*((100-percent)/100));
    }

    public void changeProducerName(Producer producer, String name){
        producer.setName(name);
        for(Product product:products){
            product.getProducer().setName(name);
        }
    }

    public ArrayList<Producer> getProducers() {
        return producers;
    }

    public ArrayList<Product> getProducts() {
        return products;
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
