package jobcafe.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import jobcafe.model.NewTicket;
import jobcafe.model.Ticket;
import jobcafe.model.TicketCategory;
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
    public ResponseEntity findTicketsByOwner(@RequestParam String owner) {
        Optional<JUser> user = userService.findByEmail(owner);
        if (user.isPresent()) {
            return new ResponseEntity<>(ticketService.findByOwner(user.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>("Ticket with owner " + owner + " not found!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity findTicketById(@PathVariable("id") int ticketId) {
        Optional<Ticket> maybeTicket = ticketService.findById(ticketId);
        if (maybeTicket.isPresent()) {
            return new ResponseEntity<>(maybeTicket.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("No ticket found with ID " + ticketId, HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/ticket/{id}")
    public ResponseEntity switchStatus(@PathVariable("id") int ticketId) {
        Optional<Ticket> maybeTicket = ticketService.findById(ticketId);
        if (maybeTicket.isPresent()) {
            Ticket ticket = maybeTicket.get();
            switch (ticket.getStatus()) {
                case OPEN:
                    ticket.setStatus(CLOSED);
                    break;
                case CLOSED:
                   ticket.setStatus(OPEN);
                    break;
            };
            System.out.println("NEW STATUS " + ticket.getStatus());
            return new ResponseEntity<>(ticketService.save(ticket), HttpStatus.OK);
        }
        return new ResponseEntity<>("No ticket found with ID " + ticketId, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/ticket")
    public ResponseEntity createTicket(@RequestBody NewTicket newTicket) {
        Optional<JUser> user = userService.findByEmail(newTicket.getOwner());
        Ticket ticket = new Ticket(newTicket.getTitle(), user.get(), new TicketCategory(newTicket.getCategory()));
//        if (!attachedFile.isEmpty()) {
//            String imageName = attachedFile.getOriginalFilename();
//            System.out.println("***** IMAGE " + imageName);
//            try {
//                InputStream imageContent = attachedFile.getInputStream();
//                Path destination = Paths.get(picturesLocation + owner.getEmail() + title + imageName);
//                System.out.println("***** DESTINATION " + destination);
//                Files.copy(imageContent, destination);
//                Message message = new Message(owner, content, ticket, destination.toUri());
//            } catch(IOException e) {
//                e.printStackTrace();
//                return new ResponseEntity<>("Unable to create ticket", HttpStatus.BAD_REQUEST);
//            }
//        } else {
            Message message = new Message(user.get(), newTicket.getContent(), ticket);

            ticketService.save(ticket);
            messageService.save(message);
            return new ResponseEntity<>("Saved", HttpStatus.CREATED);
        // }
    }

//    @GetMapping("/ticket/{id}")
//    public Ticket findTicketByIdAndOwner(@RequestParam int id, String owner) {
//        return ticketService.findByOwner(
//                userService.findByEmail(owner));
//    }
}
