package server;

public class Product {
    private String name;
    private Producer producer;
    private int price;
    private int number;
    private Type type;
    private int size;
    private int power;
    private int space;
    public Product(String name, int price, int number, int type, int special, Producer producer){
        Users.manager.getProducts().add(this);
        producer.getProducts().add(this);
        this.name = name;
        this.price = price;
        this.number = number;
        this.producer = producer;
        switch (type){
            case 1 ->{
                this.type = Type.AC;
                this.power = special;
            }
            case 2 ->{
                this.type = Type.TV;
                this.size = special;
            }
            case 3 ->{
                this.type = Type.Refrigerator;
                this.space = special;
            }
        }
    }

    public void remove(){
        Users.manager.getProducts().remove(this);
        producer.getProducts().remove(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public int getSpace() {
        return space;
    }


    public enum Type{
        TV,
        AC,
        Refrigerator
    }
}
