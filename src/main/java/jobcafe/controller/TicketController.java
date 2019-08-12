package jobcafe.controller;

import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import jobcafe.model.JUser;
import jobcafe.model.Message;
import jobcafe.model.Ticket;
import jobcafe.model.TicketCategory;
import jobcafe.model.TicketStatus;
import jobcafe.service.JUserService;
import jobcafe.service.MessageService;
import jobcafe.service.TicketService;

import static jobcafe.model.TicketStatus.*;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private JUserService userService;

    private final Path picturesLocation = Paths.get(
            String.valueOf(this.getClass().getResource("pictures")));

    @GetMapping("/ticket")
    public Iterable<Ticket> findTicketsByOwner(@RequestParam String owner) {
        return ticketService.findByOwner(
                userService.findByEmail(owner));
    }

    @GetMapping("/ticket/{id}")
    public Ticket findTicketById(@PathVariable("id") int id) {
        return ticketService.findById(id).get();
    }

    @PatchMapping("/ticket/{id}")
    public Ticket updateStatus(@PathVariable("id") int ticketId) {
        Optional<Ticket> maybeTicket = ticketService.findById(ticketId);
        Ticket ticket = maybeTicket.get();
        switch (ticket.getStatus()) {
            case OPEN: ticket.setStatus(CLOSED);
            case CLOSED: ticket.setStatus(OPEN);
        };
        return ticketService.save(ticket);
    }

    @PostMapping("/ticket")
    public Ticket createTicket(@RequestBody(required = false) String title, JUser owner, String category, String content, MultipartFile attachedFile) {

        Ticket ticket = new Ticket(title, owner, new TicketCategory(category));

        if (!attachedFile.isEmpty()) {
            String imageName = attachedFile.getOriginalFilename();
            System.out.println("***** IMAGE " + imageName);
            try {
                InputStream imageContent = attachedFile.getInputStream();
                Path destination = Paths.get(picturesLocation + owner.getEmail() + title + imageName);
                System.out.println("***** DESTINATION " + destination);
                Files.copy(imageContent, destination);
                Message message = new Message(owner, content, ticket, destination.toUri());
            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            Message message = new Message(owner, content, ticket);
        }

        return ticketService.save(ticket);
    }

//    @GetMapping("/ticket/{id}")
//    public Ticket findTicketByIdAndOwner(@RequestParam int id, String owner) {
//        return ticketService.findByOwner(
//                userService.findByEmail(owner));
//    }
}
