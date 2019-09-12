package app;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import jobcafe.controller.JUserController;
import jobcafe.service.JUserService;


@WebMvcTest(JUserController.class)
public class StandaloneControllerTests {

    @MockBean
    JUserService juserService;

    @Autowired
    MockMvc mockMvc;
}
