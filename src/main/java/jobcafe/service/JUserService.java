package jobcafe.service;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

import jobcafe.model.JUser;

public interface JUserService extends CrudRepository<JUser, Integer> {
    Optional<JUser> findByEmail(String email);
    void deleteByEmail(String email);
}
