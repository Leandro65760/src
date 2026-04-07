package com.pramps.model;

public class Product {
    private final String id;
    private final String name;
    private final String description;
    private final double price;
    private final String image;
    private final String department;

    public Product(String id, String name, String description, double price, String image, String department) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getDepartment() {
        return department;
    }
}
