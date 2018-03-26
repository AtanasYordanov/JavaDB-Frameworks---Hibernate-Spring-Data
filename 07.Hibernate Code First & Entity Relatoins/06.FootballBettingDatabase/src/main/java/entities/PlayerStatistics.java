package entities;

import javax.persistence.*;

@Entity
@Table(name = "player_statistics")
public class PlayerStatistics{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Game game;

    @ManyToOne(cascade = CascadeType.ALL)
    private Player player;

    @Column(name = "goals")
    private int goals;

    @Column(name = "assists")
    private int assists;

    @Column(name = "minutes_played")
    private int minutesPlayed;

    public PlayerStatistics() {
    }

    public PlayerStatistics(Game game, Player player, int goals, int assists, int minutesPlayed) {
        this.game = game;
        this.player = player;
        this.goals = goals;
        this.assists = assists;
        this.minutesPlayed = minutesPlayed;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(int minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }
}
