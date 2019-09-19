package jobcafe.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

import jobcafe.model.File;
import jobcafe.model.JUser;
import jobcafe.service.FileService;
import jobcafe.service.JUserService;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private AmazonS3 s3client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Autowired
    JUserService userService;



    @GetMapping("/file")
    public Iterable<File> retrieveFiles(@RequestParam String email) {
        return fileService.findByUser(email);
    }

    @PostMapping("/picture")
    public ResponseEntity postPicture(@RequestParam String user,
                                      @RequestParam MultipartFile picture) {
        java.io.File convertedFile = new java.io.File(picture.getOriginalFilename());
        try {
            FileOutputStream fos = new FileOutputStream(convertedFile);
            fos.write(picture.getBytes());
            fos.close();
            TransferManager tm = TransferManagerBuilder
                    .standard()
                    .withS3Client(s3client)
                    .build();
            tm.upload(bucket, user + "/" + picture.getOriginalFilename(), convertedFile);

            Optional<JUser> maybeJuser = userService.findByEmail(user);
            if (maybeJuser.isPresent()) {
                JUser juser = maybeJuser.get();
                juser.setProfilePicture(
                        String.valueOf(s3client.getUrl(bucket, user + "/" + picture.getOriginalFilename())));
                userService.save(juser);
            }
            return ResponseEntity.ok("Picture uploaded.");
        } catch (IOException e) {
            e.printStackTrace();
            return (ResponseEntity) ResponseEntity.badRequest();
        }
    }
}
