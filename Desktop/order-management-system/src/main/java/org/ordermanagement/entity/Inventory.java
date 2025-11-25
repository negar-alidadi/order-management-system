package org.ordermanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "INVENTORIES", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"product_id", "warehouse_id"})
})
@NamedQueries(
        @NamedQuery(
        name = "Inventory.findByProductAndWarehouse",
        query = "SELECT i FROM Inventory i WHERE i.product.id = :pid AND i.warehouse.id = :wid"
))
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Warehouse warehouse;

    @Column(nullable = false)
    private Integer quantity = 0;

    public Inventory() {}
    public Inventory(Product product, Warehouse warehouse, Integer quantity) {
        this.product = product; this.warehouse = warehouse; this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }
    public Product getProduct() {
        return product;
    }
    public Warehouse getWarehouse() {
        return warehouse;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
