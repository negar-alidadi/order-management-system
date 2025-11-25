package org.ordermanagement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "INVENTORY_TRANSACTIONS")
@NamedQueries({
        @NamedQuery(
                name = "InventoryTransaction.findAll",
                query = "SELECT t FROM InventoryTransaction t"
        ),
        @NamedQuery(
                name = "InventoryTransaction.findByInventory",
                query = "SELECT t FROM InventoryTransaction t WHERE t.inventory.id = :invId"
        ),
        @NamedQuery(
                name = "InventoryTransaction.findByType",
                query = "SELECT t FROM InventoryTransaction t WHERE t.type = :type"
        ),
        @NamedQuery(name = "InventoryTransaction.findByTypeWithInventoryAndProduct",query = "SELECT t FROM InventoryTransaction t " +
        "JOIN FETCH t.inventory inv " +
                "JOIN FETCH inv.product p " +
                "WHERE t.type = :type")
})
public class InventoryTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public enum Type { IN, OUT, ADJUSTMENT, SALE, RETURN }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Inventory inventory;

    @Enumerated(EnumType.STRING)
    private Type type;

    private Integer quantity;

    private LocalDateTime createdAt = LocalDateTime.now();

    private Long orderId;

    private String notes;

    public InventoryTransaction() {}
    public InventoryTransaction(Inventory inventory, Type type, Integer quantity, Long orderId, String notes) {
        this.inventory = inventory; this.type = type; this.quantity = quantity; this.orderId = orderId; this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
