package Problem8;

import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;

public class FindLatest10Projects {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        List<Project> projects = em.createQuery
                ("SELECT p FROM Project p ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultList();

        projects.stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.printf
                        ("Project name:%s%n\tProject Description:%s%n...%n\tProject Start Date:%s%n\tProject End Date: %s%n",
                                p.getName(), p.getDescription(), p.getStartDate(), p.getEndDate()));

        em.close();
        emf.close();
    }
}
