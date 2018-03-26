package entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private StoreLocation store;

    @Column(nullable = false)
    private LocalDate date;

    public Sale() {
    }

    public Sale(Product product, Customer customer, StoreLocation storeLocation, LocalDate date) {
        this.product = product;
        this.customer = customer;
        this.store = storeLocation;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public StoreLocation getStore() {
        return store;
    }

    public void setStore(StoreLocation storeLocation) {
        this.store = storeLocation;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
