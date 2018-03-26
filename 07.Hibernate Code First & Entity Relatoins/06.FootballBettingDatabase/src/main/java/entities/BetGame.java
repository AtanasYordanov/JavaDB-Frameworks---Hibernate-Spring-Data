package entities;

import javax.persistence.*;

@Entity
@Table(name = "bet_games")
public class BetGame{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Game game;

    @OneToOne(cascade = CascadeType.ALL)
    private Bet bet;

    @OneToOne(cascade = CascadeType.ALL)
    private ResultPrediction resultPrediction;

    public BetGame() {
    }

    public BetGame(Game game, Bet bet, ResultPrediction resultPrediction) {
        this.game = game;
        this.bet = bet;
        this.resultPrediction = resultPrediction;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public ResultPrediction getResultPrediction() {
        return resultPrediction;
    }

    public void setResultPrediction(ResultPrediction resultPrediction) {
        this.resultPrediction = resultPrediction;
    }
}
