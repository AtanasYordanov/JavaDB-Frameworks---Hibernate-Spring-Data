package app.services.api;

import app.dtos.GameOwnedView;
import app.entities.User;

import java.util.List;

public interface UserService {
    void registerUser(User user, String confirmPassword);

    User findByEmail(String email);

    List<GameOwnedView> findOwnedGames(String email);

    List<User> getAllUsers();

    void update(User user);
}
