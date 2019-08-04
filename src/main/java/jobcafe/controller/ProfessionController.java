package jobcafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jobcafe.model.Profession;
import jobcafe.service.ProfessionService;

@RestController
public class ProfessionController {

    @Autowired
    private ProfessionService professionService;

    @GetMapping("/profession")
    public Iterable<Profession> findAll() {
        return professionService.findAll();
    }
}
