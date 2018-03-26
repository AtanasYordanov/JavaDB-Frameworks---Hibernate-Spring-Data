package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "person_id")
public class Student extends Person{

    @Basic
    private Double averageGrade;

    @Basic
    private Double attendance;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL)
    private Set<Course> courses;

    public Student() {
    }

    public Student(String firstName, String lastName, String phoneNumber,
                   Double averageGrade, Double attendance, Set<Course> courses) {
        super(firstName, lastName, phoneNumber);
        this.averageGrade = averageGrade;
        this.attendance = attendance;
        this.courses = courses;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public Double getAttendance() {
        return attendance;
    }

    public void setAttendance(Double attendance) {
        this.attendance = attendance;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
