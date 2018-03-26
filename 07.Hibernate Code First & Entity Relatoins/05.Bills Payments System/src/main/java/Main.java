import entities.BillingDetail;
import entities.CreditCard;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bill_payments");
        EntityManager em = emf.createEntityManager();

        User user = new User();
        user.setFirstName("Pesho");
        user.setLastName("Peshov");
        user.setEmail("pesho.abv.bg");
        user.setPassword("1234");

        BillingDetail bd = new CreditCard
                ("324234", user, "some type", LocalDate.of(2019, 3, 4));

        em.getTransaction().begin();
        em.persist(bd);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
