package app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import app.model.User;
import app.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServiceTests {

    @Autowired
    UserService userService;

    @Test
    public void testCreateReadDelete() {
        User user = new User("Alexandre", "Krispin");
        userService.save(user);

        Iterable<User> users = userService.findAll();
        assertThat(users).extracting(User::getFirstName).containsOnly("Alexandre");

        userService.deleteAll();
        assertThat(userService.findAll()).isEmpty();
    }

}
