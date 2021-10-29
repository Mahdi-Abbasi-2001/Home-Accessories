package server;

import java.util.List;

public class Producer {
    private String name;
    private String username;
    private String password;
    private List<Product> products;
    public Producer(String name, String username, String password){
        this.name = name;
        this.username = username;
        this.password = password;
    }
    public void addProduct(Product product){
        products.add(product);
    }
    public void deleteProduct(Product product){
        products.remove(product);
    }
    public void changePrice(Product product, int price){
        product.setPrice(price);
    }
    public List<Product> showProducts(){
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
