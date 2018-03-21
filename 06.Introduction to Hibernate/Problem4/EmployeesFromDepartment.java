package Problem4;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;

public class EmployeesFromDepartment {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        List<Employee> employees = em.createQuery
                ("SELECT e FROM Employee AS e WHERE e.department.id = 6", Employee.class)
                .getResultList();

        employees.sort(Comparator.comparing(Employee::getSalary)
                .thenComparing(Employee::getId));
        for (Employee employee : employees) {
            System.out.printf("%s %s from %s - $%.2f%n", employee.getFirstName(),
                    employee.getLastName(), employee.getDepartment().getName(), employee.getSalary());
        }

        em.close();
        emf.close();
    }
}
