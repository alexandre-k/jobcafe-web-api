package jobcafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jobcafe.model.Message;
import jobcafe.model.Ticket;
import jobcafe.service.MessageService;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/message")
    public Iterable<Message> findAll(@RequestParam(required = false) Integer ticketId) {
        if (ticketId != null) {
            return messageService.findByTicketId(ticketId);
        }
        return messageService.findAll();
    }
}
