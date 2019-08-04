package jobcafe.service;

import org.springframework.data.repository.CrudRepository;

import jobcafe.model.Message;
import jobcafe.model.Ticket;

public interface MessageService extends CrudRepository<Message, Integer> {
    Iterable<Message> findByTicketId(Integer ticketId);
}
