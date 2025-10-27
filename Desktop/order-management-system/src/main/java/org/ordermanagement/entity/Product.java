package org.ordermanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PRODUCTS")
@NamedQueries(
        @NamedQuery(name = "Product.findAll", query = "select c from Product c")
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Double price;

    public Product() {}
    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price + "}";
    }
}

