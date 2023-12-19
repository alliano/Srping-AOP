package sprig.aop.aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sprig.aop.aop.helper.UserRequest;
import sprig.aop.aop.service.AccountService;
import sprig.aop.aop.service.AuthenticationService;
import sprig.aop.aop.service.PaymentService;

@SpringBootTest
class AopApplicationTests {

	private @Autowired ApplicationService applicationService;

	private @Autowired ApplicationWithAround applicationWithAround;

	private @Autowired UserService userService;

	private @Autowired AccountService accountService;

	private @Autowired AuthenticationService authenticationService;

	private @Autowired PaymentService paymentService;

	private @Autowired Greeting greeting;


	@Test
	public void testApplicationService() {
		this.applicationService.hello();
		this.applicationService.hello2();
	}

	@Test
	public void testAraound() {
		this.applicationWithAround.execute("Ahfung");
	}

	@Test
	public void testPoincutExpression() {
		this.userService.saveUser(UserRequest.builder()
			.firstName("Ahfung")
			.lastName("zheng")
			.email("ahfung@gmail.com")
			.build());
	}

	@Test
	public void testMultiplePoincut() {
		this.accountService.addAccount();
		this.authenticationService.authenticate("Ahfung", "ahfung zheng");
		this.paymentService.pay();
	}

	@Test
	public void testParseParameter() {
		this.greeting.say("Hello", "Ahfung");
	}

}
