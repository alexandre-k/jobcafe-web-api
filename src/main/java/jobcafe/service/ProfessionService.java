package jobcafe.service;

import org.springframework.data.repository.CrudRepository;

import jobcafe.model.Profession;

public interface ProfessionService extends CrudRepository<Profession, Integer> {

}
