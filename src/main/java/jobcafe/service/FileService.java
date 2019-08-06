package jobcafe.service;

import org.springframework.data.repository.CrudRepository;

import jobcafe.model.File;

public interface FileService extends CrudRepository<File, Integer> {
    Iterable<File> findByUser(String email);
}
