package jobcafe.service;

import org.springframework.data.repository.CrudRepository;

import jobcafe.model.JFile;

public interface JFileService extends CrudRepository<JFile, Integer> {
    Iterable<JFile> findByUser(String email);
}
