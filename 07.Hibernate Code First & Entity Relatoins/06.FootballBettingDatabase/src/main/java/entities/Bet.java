package entities;

import javax.persistence.*;
import java.security.Timestamp;

@Entity
@Table(name = "bets")
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "money")
    private double money;

    @Column(name = "date_time")
    private Timestamp dateAndTime;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public Bet() {
    }

    public Bet(double money, Timestamp dateAndTime, User user) {
        this.money = money;
        this.dateAndTime = dateAndTime;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Timestamp getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Timestamp dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
