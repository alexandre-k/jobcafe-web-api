package jobcafe.service;

import org.springframework.data.repository.CrudRepository;

import jobcafe.model.TicketCategory;

public interface TicketCategoryService extends CrudRepository<TicketCategory, Integer> {
}
