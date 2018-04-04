package app.services.impl;

import app.dtos.GameOwnedView;
import app.entities.Game;
import app.entities.User;
import app.repositories.UserRepository;
import app.services.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private Validator validator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.mapper = new ModelMapper();
        this.setValidator();
    }

    private void setValidator() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();
    }

    @Override
    public void registerUser(User user, String confirmPassword) {
        if (checkIfUserExists(user.getEmail())) {
            throw new IllegalArgumentException("Email is taken.");
        }
        if (!user.getPassword().equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match.");
        }
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        if (constraintViolations.isEmpty()) {
            this.userRepository.save(user);
        } else {
            throw new ConstraintViolationException("Violated constraints!", constraintViolations);
        }
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public List<GameOwnedView> findOwnedGames(String email) {
        Set<Game> games = this.userRepository.findByEmail(email).getGames();
        List<GameOwnedView> gameViews = new ArrayList<>();
        for (Game game : games) {
            GameOwnedView gameView = mapper.map(game, GameOwnedView.class);
            gameViews.add(gameView);
        }
        return gameViews;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public void update(User user) {
        this.userRepository.save(user);
    }

    private boolean checkIfUserExists(String email) {
        User user = this.findByEmail(email);
        return user != null;
    }
}
