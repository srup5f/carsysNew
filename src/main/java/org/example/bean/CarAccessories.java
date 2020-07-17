package org.example.bean;

public class CarAccessories {
    private Integer id;
    private String brand;
    private String accessoriesName;
    private Boolean availability;
    private float price;

    public CarAccessories(Integer id, String brand, String accessoriesName, Boolean availability, float price) {
        this.id = id;
        this.brand = brand;
        this.accessoriesName = accessoriesName;
        this.availability = availability;
        this.price = price;
    }

    public CarAccessories() {
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

    public String getAccessoriesName() {
        return accessoriesName;
    }

    public void setAccessoriesName(String accessoriesName) {
        this.accessoriesName = accessoriesName;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
