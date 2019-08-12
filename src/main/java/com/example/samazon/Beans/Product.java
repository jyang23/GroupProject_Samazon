package com.example.samazon.Beans;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Product_Data")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "taxable")
    private boolean taxable;

    @Column(name = "image")
    private String image;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Collection<Order> orders;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "history_id"))
//    private Collection<History> history;

    public long getId() {
        return id;
    }

    public Product(String name, String description, double price, boolean taxable, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.taxable = taxable;
        this.image = image;
    }

    public Product() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isTaxable() {
        return taxable;
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }
}
