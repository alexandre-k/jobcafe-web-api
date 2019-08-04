package jobcafe.service;

import org.springframework.data.repository.CrudRepository;
import jobcafe.model.User;

public interface UserService extends CrudRepository<User, Integer> {
    User findByEmail(String email);
    void deleteByEmail(String email);
}
