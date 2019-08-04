package app;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import app.controller.UserController;
import app.model.User;

@SpringBootTest
public class UserApplicationTests {

    @Autowired
    UserController userController;

    @Test
    public void testCreateReadDelete() {
        User user = new User("Alexandre", "Krispin");
        User userResult = userController.create(user);

        Iterable<User> users = userController.read();
        Assertions.assertThat(users).first().hasFieldOrPropertyWithValue("firstName", "Alexandre");
        userController.delete(userResult.getId());
        Assertions.assertThat(userController.read()).isEmpty();
    }
}
