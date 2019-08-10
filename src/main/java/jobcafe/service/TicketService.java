package jobcafe.service;

import org.springframework.data.repository.CrudRepository;

import jobcafe.model.JUser;
import jobcafe.model.Ticket;

public interface TicketService extends CrudRepository<Ticket, Integer> {
    Iterable<Ticket> findByOwner(JUser owner);
}
