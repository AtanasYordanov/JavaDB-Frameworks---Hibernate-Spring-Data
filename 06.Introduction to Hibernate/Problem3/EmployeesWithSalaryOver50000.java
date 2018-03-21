package Problem3;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

public class EmployeesWithSalaryOver50000 {
    public static void main(String[] args) throws IOException {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        List<Employee> employees = em.createQuery
                ("SELECT e FROM Employee AS e WHERE e.salary > 50000", Employee.class)
                .getResultList();

        for (Employee employee : employees) {
            System.out.println(employee.getFirstName());
        }

        em.close();
        emf.close();
    }
}
