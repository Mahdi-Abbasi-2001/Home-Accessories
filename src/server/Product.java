package server;

import java.awt.*;

public class Product {
    private int price;
    private String brand;
    private int number;
    private Type type;
    private int size;
    private int power;
    private int space;
    public Product(int price, String brand, int number, Type type, int special){
        this.price = price;
        this.brand = brand;
        this.number = number;
        this.type = type;
        switch (this.type){
            case TV:
                this.size = special;
            case AC:
                this.power = special;
            case Refrigerator:
                this.space = special;
        }
    }


    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
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
