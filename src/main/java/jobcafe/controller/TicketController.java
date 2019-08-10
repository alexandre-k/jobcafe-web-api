package jobcafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jobcafe.model.Ticket;
import jobcafe.service.JUserService;
import jobcafe.service.TicketService;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private JUserService userService;

    @GetMapping("/ticket")
    public Iterable<Ticket> findByOwner(@RequestParam String owner) {
        return ticketService.findByOwner(
                userService.findByEmail(owner));
    }
}
