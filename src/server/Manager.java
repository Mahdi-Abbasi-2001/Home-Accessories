package server;

import java.util.List;

public class Manager {
    private String name;
    private List<Producer> producers;
    private List<Product> products;
    private String username;
    private String password;
    public Manager(String username, String password){
        this.username = username;
        this.password = password;
    }
    public void setDiscount(int percent){
        for(Product product:products)
            product.setPrice(product.getPrice()*((100-percent)/100));
    }
    public List<Product> showProducts(){
        return products;
    }
    public List<Producer> showProducers(){
        return producers;
    }
    public void changeProducerName(Producer producer, String name){
        producer.setName(name);
        for(Product product:products){
            product.setBrand(name);
        }
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
