package jobcafe.service;

import org.springframework.data.repository.CrudRepository;

import jobcafe.model.Ticket;
import jobcafe.model.User;

public interface TicketService extends CrudRepository<Ticket, Integer> {
    Iterable<Ticket> findByOwner(User owner);
}
