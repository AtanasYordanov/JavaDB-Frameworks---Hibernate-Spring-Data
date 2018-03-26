package entities;

import javax.persistence.*;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "logo")
    private byte[] logo;

    @Column(name = "initials")
    private String initials;

    @ManyToOne(cascade = CascadeType.ALL)
    private Color primaryColorKit;

    @ManyToOne(cascade = CascadeType.ALL)
    private Color secondaryColorKit;

    @ManyToOne(cascade = CascadeType.ALL)
    private Town town;

    @Column(name = "budget")
    private Double budget;

    public Team() {
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

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public Color getPrimaryColorKit() {
        return primaryColorKit;
    }

    public void setPrimaryColorKit(Color primaryColorKit) {
        this.primaryColorKit = primaryColorKit;
    }

    public Color getSecondaryColorKit() {
        return secondaryColorKit;
    }

    public void setSecondaryColorKit(Color secondaryColorKit) {
        this.secondaryColorKit = secondaryColorKit;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }
}
