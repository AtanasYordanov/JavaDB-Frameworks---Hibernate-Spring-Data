package app.runners;

import app.entities.User;
import app.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class ConsoleRunner implements CommandLineRunner{

    private UserService userService;

    @Autowired
    public ConsoleRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        //insertUsers();
        deleteInactiveUsers();
        printAllUsersByEmailProvider("gmail.com");
    }

    private void deleteInactiveUsers() {
        this.userService.deleteInactiveUsers(LocalDate.of(2015, 1, 1));
    }

    private void insertUsers() {
        User user = new User();
        user.setUsername("pesho12");
        user.setPassword("Pesho99");
        user.setEmail("pesho@gmail.com");
        user.setAge(22);
        user.setLastTimeLoggedIn(LocalDateTime.of
                (LocalDate.of(2020, 2, 2),
                        LocalTime.of(12, 50)));
        this.userService.save(user);
    }

    private void printAllUsersByEmailProvider(String provider) {
        for (User user : this.userService.getAllUsersByEmailProvider(provider)) {
            System.out.println(user.getUsername()+ " " + user.getEmail());
        }
    }
}
