package com.iroshnk.dropwizarde.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private int id ;
    @Version
    @Column(name = "version")
    private int version = 0;

    @Column
    private int quantity;

    @ManyToOne
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
}
