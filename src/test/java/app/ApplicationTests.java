package jobcafe;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jobcafe.controller.JUserController;

@SpringBootTest
class ApplicationTests {

	@Autowired
	JUserController juserController;

}
