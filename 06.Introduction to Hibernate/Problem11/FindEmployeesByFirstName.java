package Problem11;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FindEmployeesByFirstName {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pattern = reader.readLine();

        List<Employee> employees = em.createQuery("SELECT e FROM Employee e WHERE SUBSTRING(e.firstName, 1, :len) = :pattern", Employee.class)
                .setParameter("len", pattern.length())
                .setParameter("pattern", pattern)
                .getResultList();

        for (Employee employee : employees) {
            System.out.printf("%s %s - %s - ($%.2f)%n", employee.getFirstName(), employee.getLastName(),
                    employee.getJobTitle(), employee.getSalary());
        }

        em.close();
        emf.close();
    }
}
