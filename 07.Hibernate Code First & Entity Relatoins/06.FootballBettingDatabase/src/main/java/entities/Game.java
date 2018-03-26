package entities;

import javax.persistence.*;
import java.security.Timestamp;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Team homeTeam;

    @OneToOne(cascade = CascadeType.ALL)
    private Team awayTeam;

    @Column(name = "home_team_goals")
    private int homeTeamGoals;

    @Column(name = "away_team_goals")
    private int awayTeamGoals;

    @Column(name = "date_time")
    private Timestamp dateAndTime;

    @Column(name = "home_team_bet_rate")
    private double homeTeamBetRate;

    @Column(name = "away_team_bet_rate")
    private double awayTeamBetRate;

    @Column(name = "draw_game_bet_rate")
    private double drawGameBetRate;

    @ManyToOne(cascade = CascadeType.ALL)
    private Round round;

    @ManyToOne(cascade = CascadeType.ALL)
    private Competition competition;

    public Game() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(int homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(int awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    public Timestamp getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Timestamp dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public double getHomeTeamBetRate() {
        return homeTeamBetRate;
    }

    public void setHomeTeamBetRate(double homeTeamBetRate) {
        this.homeTeamBetRate = homeTeamBetRate;
    }

    public double getAwayTeamBetRate() {
        return awayTeamBetRate;
    }

    public void setAwayTeamBetRate(double awayTeamBetRate) {
        this.awayTeamBetRate = awayTeamBetRate;
    }

    public double getDrawGameBetRate() {
        return drawGameBetRate;
    }

    public void setDrawGameBetRate(double drawGameBetRate) {
        this.drawGameBetRate = drawGameBetRate;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}
