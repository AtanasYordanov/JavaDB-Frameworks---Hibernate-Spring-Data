package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 60)
    private String lastName;

    @Column(nullable = false, length = 200)
    private String address;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false, columnDefinition = "BLOB")
    private byte[] picture;

    @Column(nullable = false, name = "medical_insurance")
    private boolean hasMedicalInsurance;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private Set<Visitation> visitations;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "patients_diagnoses",
    joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "diagnosis_id", referencedColumnName = "id"))
    private Set<Diagnosis> diagnoses;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "patients_medicaments",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id"))
    private Set<Medicament> prescribedMedicaments;

    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        Pattern pattern = Pattern.compile
                ("^[A-Za-z0-9][A-Za-z0-9+_.-]+[A-Za-z0-9]@([A-Za-z0-9+_.-]+)\\.([A-Za-z0-9+]+)$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.find()){
            throw new IllegalArgumentException("Invalid email");
        }
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public boolean isHasMedicalInsurance() {
        return hasMedicalInsurance;
    }

    public void setHasMedicalInsurance(boolean hasMedicalInsurance) {
        this.hasMedicalInsurance = hasMedicalInsurance;
    }

    public Set<Visitation> getVisitations() {
        return visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }

    public Set<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnosis> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public Set<Medicament> getPrescribedMedicaments() {
        return prescribedMedicaments;
    }

    public void setPrescribedMedicaments(Set<Medicament> prescribedMedicaments) {
        this.prescribedMedicaments = prescribedMedicaments;
    }
}
