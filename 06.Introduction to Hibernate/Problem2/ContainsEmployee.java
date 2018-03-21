package Problem2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ContainsEmployee {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = reader.readLine().split("\\s+");
        String firstName = tokens[0];
        String lastName = tokens[1];

        Query query = em.createQuery(
                "SELECT e FROM Employee AS e WHERE e.firstName = :firstName AND e.lastName = :lastName"
        ).setParameter("firstName", firstName).setParameter("lastName", lastName);

        if (query.getResultList().size() > 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        em.close();
        emf.close();
    }
}
