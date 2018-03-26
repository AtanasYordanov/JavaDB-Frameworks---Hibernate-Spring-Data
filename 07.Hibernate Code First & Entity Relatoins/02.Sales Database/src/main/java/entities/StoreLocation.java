package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "store_location")
public class StoreLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location_name", nullable = false, length = 150)
    private String locationName;

    @OneToMany(mappedBy = "store")
    private Set<Sale> sales;

    public StoreLocation() {
    }

    public StoreLocation(String locationName, Set<Sale> sales) {
        this.locationName = locationName;
        this.sales = sales;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
