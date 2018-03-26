package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String nameDescription;

    @Basic
    private LocalDate startDate;

    @Basic
    private LocalDate endDate;

    @Basic
    private Integer credits;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "courses_students",
    joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "person_id"))
    private Set<Student> students;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", foreignKey = @ForeignKey(name = "courses_teachers"))
    private Teacher teacher;

    public Course() {
    }

    public Course(String nameDescription, LocalDate startDate, LocalDate endDate, Integer credits) {
        this.nameDescription = nameDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.credits = credits;
    }

    public Course(String nameDescription, LocalDate startDate, LocalDate endDate, Integer credits, Set<Student> students, Teacher teacher) {
        this.nameDescription = nameDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.credits = credits;
        this.students = students;
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameDescription() {
        return nameDescription;
    }

    public void setNameDescription(String nameDescription) {
        this.nameDescription = nameDescription;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
