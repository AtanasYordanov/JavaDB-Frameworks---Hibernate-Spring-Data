package Problem5;

import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddingNewAddressAndUpdatingEmployee {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Address address = new Address();
        address.setText("Vitoshka 15");

        Town town = new Town();
        town.setName("Sofia");
        address.setTown(town);

        em.persist(town);
        em.persist(address);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String lastName = reader.readLine();

        Employee employee = em.createQuery
                ("SELECT e FROM Employee AS e WHERE e.lastName = :lastName", Employee.class)
                .setParameter("lastName", lastName)
                .getSingleResult();

        employee.setAddress(address);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
