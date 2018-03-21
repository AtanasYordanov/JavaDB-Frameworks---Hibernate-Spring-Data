package Problem1;

import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class RemoveObjects {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        List<Town> towns = em.createQuery("SELECT t FROM Town t WHERE LENGTH(t.name) > 5", Town.class).<Town>getResultList();
        for (Town town : towns) {
            town.setName(town.getName().toLowerCase());
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
