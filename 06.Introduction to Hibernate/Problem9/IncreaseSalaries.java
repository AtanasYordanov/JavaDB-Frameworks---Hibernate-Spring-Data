package Problem9;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class IncreaseSalaries {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        List<Employee> employees = em.createQuery
                ("SELECT e FROM Employee e WHERE e.department.name IN\n" +
                                "('Engineering', 'Tool Design', 'Marketing', 'Information Services')",
                        Employee.class)
                .getResultList();

        for (Employee employee : employees) {
            employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.12)));
        }

        employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .forEach(e -> System.out.printf("%s %s ($%.2f)%n",
                        e.getFirstName(), e.getLastName(), e.getSalary()));

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
