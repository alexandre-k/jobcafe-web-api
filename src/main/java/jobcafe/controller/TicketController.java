package jobcafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jobcafe.model.Ticket;
import jobcafe.model.User;
import jobcafe.service.TicketService;
import jobcafe.service.UserService;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @GetMapping("/ticket")
    public Iterable<Ticket> findByOwner(@RequestParam String owner) {
        return ticketService.findByOwner(
                userService.findByEmail(owner));
    }
}
