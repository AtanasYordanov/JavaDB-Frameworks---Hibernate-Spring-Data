import entities.Course;
import entities.Student;
import entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("university_system");
        EntityManager em = emf.createEntityManager();

        LocalDate date1 = LocalDate.of(2018, 1, 2);
        LocalDate date2 = LocalDate.of(2018, 1, 2);
        LocalDate date3 = LocalDate.of(2018, 1, 2);

        Course course1 = new Course("Course1", date1, date2, 8);
        Course course2 = new Course("Course2", date1, date3, 12);
        Course course3 = new Course("Course3", date2, date3, 14);

        Teacher teacher1 = new Teacher("Gosho", "Goshov", "021312312",
                "goshkov@gmail.com", 20.00, new HashSet<>());
        teacher1.getCourses().add(course1);
        teacher1.getCourses().add(course2);

        Teacher teacher2 = new Teacher("Grigor", "Grigorov", "21312321",
                "ggg@gmail.com", 18.50, new HashSet<>());
        teacher2.getCourses().add(course3);

        Student student = new Student("Pesho", "Peshov", "0888888888", 5.5,
                80.8, new HashSet<>());
        student.getCourses().add(course1);
        student.getCourses().add(course2);
        student.getCourses().add(course3);

        course1.setTeacher(teacher1);
        course2.setTeacher(teacher1);
        course3.setTeacher(teacher2);

        course1.setStudents(new HashSet<>());
        course2.setStudents(new HashSet<>());
        course3.setStudents(new HashSet<>());

        course1.getStudents().add(student);
        course2.getStudents().add(student);
        course3.getStudents().add(student);

        em.getTransaction().begin();

        em.persist(student);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
