package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teachers")
@PrimaryKeyJoinColumn(name = "person_id")
public class Teacher extends Person{

    @Column(length = 50)
    private String email;

    @Basic
    private Double salaryPerHour;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private Set<Course> courses;

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String phoneNumber,
                   String email, Double salaryPerHour, Set<Course> course) {
        super(firstName, lastName, phoneNumber);
        this.email = email;
        this.salaryPerHour = salaryPerHour;
        this.courses = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(Double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> course) {
        this.courses = course;
    }
}
