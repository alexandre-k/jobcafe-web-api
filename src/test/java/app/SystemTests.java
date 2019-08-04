package app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import app.model.User;

public class SystemTests {

    @Test
    public void testCreateReadDelete() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user";
        User user = new User("Alexandre", "Krispin");
        ResponseEntity<User> entity = restTemplate.postForEntity(url, user, User.class);
        Assertions.assertEquals(entity.getStatusCode(), HttpStatus.OK);

        User[] users = restTemplate.getForObject(url, User[].class);
        User user1 = users[0];
        assertThat(user1.getFirstName(), equalTo("Alexandre"));

        restTemplate.delete(url + "/" + entity.getBody().getId());
        users = restTemplate.getForObject(url, User[].class);

        assertThat(users, equalTo(0));
    }
}
