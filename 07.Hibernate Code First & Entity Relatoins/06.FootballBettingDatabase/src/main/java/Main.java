import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("football_betting_db");
        EntityManager em = emf.createEntityManager();

        Continent continent1 = new Continent("Europe");
        Continent continent2 = new Continent("America");

        Country country1 = new Country("BUL", "Bulgaria", continent1);
        Country country2 = new Country("USA", "United States", continent2);
        Country country3 = new Country("GER", "Germany", continent1);
        Country country4 = new Country("FRA", "France", continent1);

        Town town1 = new Town("Sofia", country1);
        Town town2 = new Town("Plovdiv", country1);
        Town town3 = new Town("New York", country2);
        Town town4 = new Town("Paris", country4);
        Town town5 = new Town("Berlin", country3);

        Color color1 = new Color("Red");
        Color color2 = new Color("Yellow");
        Color color3 = new Color("Green");
        Color color4 = new Color("Blue");

        Team team1 = new Team();
        team1.setInitials("AA");
        team1.setLogo(new byte[2]);
        team1.setName("team1");
        team1.setPrimaryColorKit(color1);
        team1.setSecondaryColorKit(color2);
        team1.setTown(town1);
        team1.setBudget(23123.12);

        Team team2 = new Team();
        team2.setInitials("BB");
        team2.setLogo(new byte[4]);
        team2.setName("team2");
        team2.setPrimaryColorKit(color3);
        team2.setSecondaryColorKit(color4);
        team2.setTown(town3);
        team2.setBudget(89897.12);

        Position pos1 = new Position("GK", "Goalkeeper");
        Position pos2 = new Position("MF", "Midfielder");

        Player player1 = new Player();
        player1.setName("Gosho");
        player1.setPosition(pos1);
        player1.setSquadNumber(11);
        player1.setCurrentlyInjured(false);
        player1.setPosition(pos1);

        Player player2 = new Player();
        player2.setName("Joro");
        player2.setPosition(pos2);
        player2.setSquadNumber(3);
        player2.setCurrentlyInjured(true);
        player2.setPosition(pos2);

        Round round = new Round("final");
        CompetitionType competitionType = new CompetitionType("international");
        Competition competition = new Competition("World-Cup", competitionType);

        Game game = new Game();
        game.setHomeTeam(team1);
        game.setAwayTeam(team2);
        game.setHomeTeamBetRate(1.5);
        game.setAwayTeamBetRate(2.5);
        game.setDrawGameBetRate(2);
        game.setHomeTeamGoals(1);
        game.setAwayTeamGoals(2);
        game.setDateAndTime(null);
        game.setRound(round);
        game.setCompetition(competition);

        PlayerStatistics ps1 = new PlayerStatistics(game, player1, 1, 1, 50);
        PlayerStatistics ps2 = new PlayerStatistics(game, player2, 0, 1, 80);

        User user1 = new User();
        user1.setFullName("Stamen Stamenov");
        user1.setUsername("stam123");
        user1.setPassword("stam321");
        user1.setEmail("stam123@abv.bg");
        user1.setBalance(100.50);

        User user2 = new User();
        user2.setFullName("Angel Angelov");
        user2.setUsername("ang123");
        user2.setPassword("ang321");
        user2.setEmail("ang123@abv.bg");

        Bet bet1 = new Bet(50, null, user1);
        Bet bet2 = new Bet(150, null, user2);

        ResultPrediction rp1 = new ResultPrediction("Home team wins");
        ResultPrediction rp2 = new ResultPrediction("Away team wins");

        BetGame betGame1 = new BetGame(game, bet1, rp1);
        BetGame betGame2 = new BetGame(game, bet2, rp2);

        ///////////////////
        //Start Transaction
        ///////////////////
        em.getTransaction().begin();

        em.persist(town1);
        em.persist(town2);
        em.persist(town3);
        em.persist(town4);
        em.persist(town5);

        em.persist(team1);
        em.persist(team2);

        em.persist(game);

        em.persist(user1);
        em.persist(user2);

        em.persist(bet1);
        em.persist(bet2);

        em.persist(ps1);
        em.persist(ps2);

        em.persist(betGame1);
        em.persist(betGame2);

        em.getTransaction().commit();
        /////////////////
        //End Transaction
        /////////////////
        em.close();
        emf.close();
    }
}
