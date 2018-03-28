package app.services.api;

import app.entities.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    List<User> getAllUsersByEmailProvider(String provider);

    void deleteInactiveUsers(LocalDate date);

    void save(User user);
}
