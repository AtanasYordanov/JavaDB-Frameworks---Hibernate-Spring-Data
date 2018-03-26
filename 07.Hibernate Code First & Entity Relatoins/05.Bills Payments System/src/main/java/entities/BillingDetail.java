package entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BillingDetail {
    @Id
    @Column(name = "number", length = 20)
    private String number;

    @ManyToOne(cascade = CascadeType.ALL)
    private User owner;

    public BillingDetail() {
    }

    public BillingDetail(String number, User owner) {
        this.number = number;
        this.owner = owner;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
