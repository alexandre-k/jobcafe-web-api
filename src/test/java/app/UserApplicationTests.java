package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jobcafe.controller.JUserController;

@SpringBootTest
public class UserApplicationTests {

    @Autowired
    JUserController juserController;
}
