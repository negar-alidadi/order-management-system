package org.ordermanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ORDER_ITEMS")
@NamedQueries(
        @NamedQuery(name = "OrderItem.findAll", query = "select c from OrderItem c")
)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    private Order order;

    @ManyToOne(optional = false)
    private Product product;

    private int quantity;
    private double price;

    public OrderItem() {}

    public OrderItem(Product product, int quantity, double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItem{id=" + id +
                ", product=" + product.getName() +
                ", quantity=" + quantity +
                ", price=" + price + "}";
    }
}
