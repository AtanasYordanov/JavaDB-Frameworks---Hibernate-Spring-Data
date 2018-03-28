package app.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "birthTown")
    private Set<User> bornUsers;

    @OneToMany(mappedBy = "currentTown")
    private Set<User> currentlyLivingUsers;

    public Town() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<User> getBornUsers() {
        return bornUsers;
    }

    public void setBornUsers(Set<User> bornUsers) {
        this.bornUsers = bornUsers;
    }

    public Set<User> getCurrentlyLivingUsers() {
        return currentlyLivingUsers;
    }

    public void setCurrentlyLivingUsers(Set<User> currentlyLivingUsers) {
        this.currentlyLivingUsers = currentlyLivingUsers;
    }
}
