package jobcafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import jobcafe.model.JUser;
import jobcafe.model.Message;
import jobcafe.model.Ticket;
import jobcafe.service.JUserService;
import jobcafe.service.MessageService;
import jobcafe.service.TicketService;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private JUserService jUserService;

    @GetMapping("/message")
    public Iterable<Message> findAll(@RequestParam(required = false) Integer ticketId) {
        if (ticketId != null) {
            return messageService.findByTicketId(ticketId);
        }
        return messageService.findAll();
    }

    @PostMapping("/message")
    public Message create(@RequestBody String author, String content, Integer ticketId, String createdDate, MultipartFile attachedFile) {
        System.out.println("GOT A NEW MESSAGE !!" + content);
        if (!attachedFile.isEmpty()) {
            // TODO
            System.out.println("Add logic to save an attached file");
        }
        Optional<Ticket> ticket = ticketService.findById(ticketId);
        JUser user = jUserService.findByEmail(author);
        Message message = new Message(user, content, ticket.get());
        return message;
    }
}
