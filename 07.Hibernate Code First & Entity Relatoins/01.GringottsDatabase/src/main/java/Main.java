import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gringotts");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Wizard wizard = new Wizard("fName", "lName",
                "notes", 11, "creator",
                13, "dGroup", new Timestamp(1L),
                500.50, 1.15, 2.50,
                new Timestamp(2L), false);

        em.persist(wizard);

        em.getTransaction().commit();
        em.close();
		emf.close();
    }
}
