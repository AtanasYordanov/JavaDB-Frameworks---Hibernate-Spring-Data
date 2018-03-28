package app.entities;


import app.annotations.Password;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    @Length(min = 4, max = 30)
    private String username;

    @Column(name = "password", nullable = false)
    @Password(
            minLength = 5,
            maxLength = 40,
            containsDigit = true,
            containsUpperCase = true
    )
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9]+[-_.]*[a-zA-Z0-9]+@[a-zA-Z0-9]+[-_.]*[a-zA-Z0-9](\\.[a-zA-Z0-9]+[-_.]*[a-zA-Z0-9]+)+$"
            , message = "Please provide a valid email address")
    private String email;

    @Column(name = "profile_picture", columnDefinition = "LONGBLOB")
    @Size(max = 1000000)
    private byte[] profilePicture;

    @Column(name = "registered_on")
    private LocalDateTime registeredOn;

    @Column(name = "last_time_logged_in")
    private LocalDateTime lastTimeLoggedIn;

    @Column(name = "age")
    @Min(1)
    @Max(120)
    private Integer age;

    @Column(name = "id_deleted")
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "birth_town")
    private Town birthTown;

    @ManyToOne
    @JoinColumn(name = "current_town")
    private Town currentTown;

    @OneToMany(mappedBy = "owner")
    private Set<Album> albums;

    public User() {
    }

    public Long getId() {
        return id;
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

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDateTime registeredOn) {
        this.registeredOn = registeredOn;
    }

    public LocalDateTime getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(LocalDateTime lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Town getBirthTown() {
        return birthTown;
    }

    public void setBirthTown(Town birthTown) {
        this.birthTown = birthTown;
    }

    public Town getCurrentTown() {
        return currentTown;
    }

    public void setCurrentTown(Town currentTown) {
        this.currentTown = currentTown;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
