package jobcafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jobcafe.model.File;
import jobcafe.service.FileService;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/file")
    public Iterable<File> retrieveFiles(@RequestParam String email) {
        return fileService.findByUser(email);
    }
}
