package org.example.bean;

public class Car {
    private Integer id;
    private String brand;
    private String type;
    private Float price;

    public Car(Integer id, String brand, String type, Float price) {
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.price = price;
    }

    public Car() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
