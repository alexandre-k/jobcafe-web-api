package jobcafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jobcafe.model.TicketCategory;
import jobcafe.service.TicketCategoryService;

@RestController
public class TicketCategoryController {

    @Autowired
    private TicketCategoryService ticketCategoryService;

    @GetMapping("/ticket-category")
    public Iterable<TicketCategory> findAll() {
        return ticketCategoryService.findAll();
    }
}
