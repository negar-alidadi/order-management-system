package org.ordermanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "WAREHOUSES")
@NamedQueries({
        @NamedQuery(
                name = "Warehouse.findAll",
                query = "SELECT w FROM Warehouse w"
        ),
        @NamedQuery(
                name = "Warehouse.findByName",
                query = "SELECT w FROM Warehouse w WHERE w.name = :name"
        )
})

public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String location;

    public Warehouse() {}
    public Warehouse(String name, String location) { this.name = name; this.location = location; }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getLocation() {
        return location;
    }
}
