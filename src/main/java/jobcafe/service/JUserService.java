package jobcafe.service;

import org.springframework.data.repository.CrudRepository;

import jobcafe.model.JUser;

public interface JUserService extends CrudRepository<JUser, Integer> {
    JUser findByEmail(String email);
    void deleteByEmail(String email);
}
