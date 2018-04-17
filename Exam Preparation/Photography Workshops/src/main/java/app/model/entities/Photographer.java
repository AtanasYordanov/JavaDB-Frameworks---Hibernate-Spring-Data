package app.model.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "photographers")
public class Photographer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Length(min = 2, max = 50)
    @Column(name = "last_name")
    private String lastName;

    @Pattern(regexp = "^\\+\\d{1,3}/\\d{8,10}$")
    @Column(name = "phone")
    private String phone;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "primary_camera_id", referencedColumnName = "id")
    private BasicCamera primaryCamera;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "secondary_camera_id", referencedColumnName = "id")
    private BasicCamera secondaryCamera;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Lens> lenses;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Accessory> accessories;

    @ManyToMany(mappedBy = "participants", cascade = CascadeType.ALL)
    private Set<Workshop> workshops;

    public Photographer() {
        this.lenses = new HashSet<>();
        this.accessories = new HashSet<>();
        this.workshops = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BasicCamera getPrimaryCamera() {
        return primaryCamera;
    }

    public void setPrimaryCamera(BasicCamera primaryCamera) {
        this.primaryCamera = primaryCamera;
    }

    public BasicCamera getSecondaryCamera() {
        return secondaryCamera;
    }

    public void setSecondaryCamera(BasicCamera secondaryCamera) {
        this.secondaryCamera = secondaryCamera;
    }

    public Set<Lens> getLenses() {
        return lenses;
    }

    public void setLenses(Set<Lens> lenses) {
        this.lenses = lenses;
    }

    public Set<Accessory> getAccessories() {
        return accessories;
    }

    public void setAccessories(Set<Accessory> accessories) {
        this.accessories = accessories;
    }

    public Set<Workshop> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(Set<Workshop> workshops) {
        this.workshops = workshops;
    }
}
