package Problem12;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class EmployeesMaximumSalaries {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        List<Object[]> result = em.createQuery("" +
                "SELECT d.name, MAX(e.salary) FROM Department AS d " +
                "INNER JOIN Employee AS e " +
                "ON e.department.id = d.id " +
                "GROUP BY d.name " +
                "HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000 " +
                "ORDER BY d.id", Object[].class)
                .getResultList();

        for (Object[] tokens : result) {
            System.out.printf("%s - %.2f%n", tokens[0], (BigDecimal) tokens[1]);
        }

        em.close();
        emf.close();
    }
}
