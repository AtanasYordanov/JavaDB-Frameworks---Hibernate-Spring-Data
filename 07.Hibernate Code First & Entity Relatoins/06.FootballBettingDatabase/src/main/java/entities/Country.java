package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @Column(length = 3)
    private String id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Continent continent;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private Set<Town> towns;

    public Country() {
    }

    public Country(String id, String name, Continent continent) {
        this.id = id;
        this.name = name;
        this.continent = continent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public Set<Town> getTowns() {
        return towns;
    }

    public void setTowns(Set<Town> towns) {
        this.towns = towns;
    }
}
