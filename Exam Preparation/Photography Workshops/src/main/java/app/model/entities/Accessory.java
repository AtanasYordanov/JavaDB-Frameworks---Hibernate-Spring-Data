package app.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "accessories")
public class Accessory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Photographer owner;

    public Accessory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Photographer getOwner() {
        return owner;
    }

    public void setOwner(Photographer owner) {
        this.owner = owner;
    }
}
