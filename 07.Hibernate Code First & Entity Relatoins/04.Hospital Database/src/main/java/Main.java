import entities.Diagnosis;
import entities.Medicament;
import entities.Patient;
import entities.Visitation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hospital");
        EntityManager em = emf.createEntityManager();

        Medicament m1 = new Medicament("Some Medicament");
        Medicament m2 = new Medicament("Another Medicament");

        Diagnosis d1 = new Diagnosis("Diagnosis 1", "Some comments");
        Diagnosis d2 = new Diagnosis("Diagnosis 2", "Other comments");

        Patient p1 = new Patient();
        p1.setFirstName("Pesho");
        p1.setLastName("Peshov");
        p1.setAddress("Some address");
        p1.setEmail("pesho@abv.bg");
        p1.setDateOfBirth(LocalDate.of(1992, 10, 10));
        p1.setPicture(new byte[5]);
        p1.setHasMedicalInsurance(true);

        p1.setPrescribedMedicaments(new HashSet<>());
        p1.getPrescribedMedicaments().add(m1);
        p1.getPrescribedMedicaments().add(m2);

        Visitation v1 = new Visitation
                (LocalDate.of(2018, 1, 1), "Some comments", p1);
        Visitation v2 = new Visitation
                (LocalDate.of(2018, 1, 1), "Other comments", p1);

        p1.setVisitations(new HashSet<>());
        p1.getVisitations().add(v1);
        p1.getVisitations().add(v2);

        p1.setDiagnoses(new HashSet<>());
        p1.getDiagnoses().add(d1);
        p1.getDiagnoses().add(d2);

        em.getTransaction().begin();
        em.persist(p1);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
