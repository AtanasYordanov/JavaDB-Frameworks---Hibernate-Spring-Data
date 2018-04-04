package app.services.api;

import app.dtos.GameDetailsView;
import app.dtos.GameTitlePriceView;
import app.entities.Game;

import java.util.List;

public interface GameService {
    Game findById(Long id);

    Game findByTitle(String title);

    List<GameTitlePriceView> findAll();

    GameDetailsView findGameDetails(String title);

    void add(Game game);

    void update(Game game);

    void remove(Game game);
}
