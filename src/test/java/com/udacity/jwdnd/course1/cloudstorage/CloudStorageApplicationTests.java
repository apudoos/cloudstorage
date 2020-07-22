package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;
	private SignupPage signupPage;
	private LoginPage loginPage;
	private HomePage homePage;
	private HomePageCred homePageCred;
	public String baseURL;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	//Write Tests for User SignUp, Login, and Unauthorized Access Restrictions
	public void testLoginAndLogout() {

		//1. Invalid User Login Test
		String message1 = new String("Invalid username or password");
		driver.get("http://localhost:" + this.port + "/login");
		loginPage = new LoginPage(driver);
		loginPage.login("sammat2", "nopass");
		assertEquals(message1, loginPage.errorMessageDisplay());

		//2. Test Signup
		driver.get("http://localhost:" + this.port + "/signup");
		signupPage = new SignupPage(driver);
		signupPage.signUpUser("Sam", "Mat", "sammat", "nopass");
		String message = new String("You successfully signed up! Please continue to the login page.");
		assertEquals(message, signupPage.successMessageDisplay());

		//3. Valid User Login Test
		driver.get("http://localhost:" + this.port + "/login");
		loginPage = new LoginPage(driver);
		loginPage.login("sammat", "nopass");
		//System.out.println(driver.getCurrentUrl() + " " + driver.getTitle());
		assertEquals("Home", driver.getTitle());

		//4. Logout Functionality
		driver.get("http://localhost:" + this.port + "/home");
		homePage = new HomePage(driver);
		homePage.logOut();
		//System.out.println(driver.getCurrentUrl() + " " + driver.getTitle());
		assertEquals("Login", driver.getTitle());

		//5. Unauthorized Access
		driver.get("http://localhost:" + this.port + "/home");
		assertEquals("Login", driver.getTitle());
	}

	@Test
	//Write Tests for User SignUp, Login, and Unauthorized Access Restrictions
	public void testNavNotes() {
		//Test Note Creation
		//1. Create User
		driver.get("http://localhost:" + this.port + "/signup");
		signupPage = new SignupPage(driver);
		signupPage.signUpUser("NotesMan", "Mat", "notesuser", "nopass");

		//2. Login using User
		driver.get("http://localhost:" + this.port + "/login");
		loginPage = new LoginPage(driver);
		loginPage.login("notesuser", "nopass");

		//3. Go to note tab and create a new note
		driver.get("http://localhost:" + this.port + "/home");
		homePage = new HomePage(driver);
		String tabTitle = homePage.navNotesTabClick(driver);
		System.out.println(tabTitle);
		assertEquals("Notes", tabTitle);
		homePage.addNoteButtonClick(driver);
		homePage.addNote("First", "First Message, 0000", driver);
		assertEquals("Success", homePage.successMsgText());

		//4. Validate that the message is correctly updated in the Home page
		driver.get("http://localhost:" + this.port + "/home");
		System.out.println(driver.getCurrentUrl() + " " + driver.getTitle());
		System.out.println("Back to home page");
		String tabTitle2 = homePage.navNotesTabClick(driver);
		assertEquals("First First Message, 0000", homePage.readNotesList(driver, 0));

		//5. Test Edit Button
		homePage.clickEditNote();
		homePage.editNote("Second", "Edited Message, 1234", driver);
		assertEquals("Success", homePage.successMsgText());
		driver.get("http://localhost:" + this.port + "/home");
		homePage.navNotesTabClick(driver);
		assertEquals("Second Edited Message, 1234", homePage.readNotesList(driver, 0));

		//6. Test Delete Button
		homePage.addNoteButtonClick(driver);
		homePage.addNote("Third", "Third Message, 3333", driver);
		driver.get("http://localhost:" + this.port + "/home");
		homePage.navNotesTabClick(driver);
		assertEquals("Third Third Message, 3333", homePage.readNotesList(driver, 1));
		homePage.deleteNote();
		System.out.println("Note deleted");
		driver.get("http://localhost:" + this.port + "/home");
		homePage.navNotesTabClick(driver);
		assertNotEquals("Third Third Message, 3333", homePage.readNotesList(driver, 0));

		//7. Logout
		homePage.logOut();

	}

	@Test
	//Test the Credentials Tab
	public void testNavCred() {
		//Test Credential Creation
		//1. Create User
		driver.get("http://localhost:" + this.port + "/signup");
		signupPage = new SignupPage(driver);
		signupPage.signUpUser("CredMan", "Mat", "creduser", "nopass");

		//2. Login using User
		driver.get("http://localhost:" + this.port + "/login");
		loginPage = new LoginPage(driver);
		loginPage.login("creduser", "nopass");

		//3. Go to credential tab and create a new credential
		driver.get("http://localhost:" + this.port + "/home");
		homePageCred = new HomePageCred(driver);
		String tabTitle = homePageCred.navCredTabClick(driver);
		System.out.println(tabTitle);
		assertEquals("Credentials", tabTitle);
		homePageCred.addCredButtonClick(driver);
		homePageCred.addCred("cnbc.com", "sammat","matsam", driver);
		assertEquals("Success", homePageCred.successMsgText());

		//4. Validate that the message is correctly updated in the Home page
		driver.get("http://localhost:" + this.port + "/home");
		homePageCred.navCredTabClick(driver);
		assertNotEquals("cnbc.com sammat matsam", homePageCred.readCredList(driver, 0));

		//5. Test Edit Button
		homePageCred.clickEditCred();
		homePageCred.editCred("cnbc.com", "samduck","matsamduck", driver);
		assertEquals("Success", homePageCred.successMsgText());
		System.out.println("this is completed.");
		driver.get("http://localhost:" + this.port + "/home");
		homePageCred.navCredTabClick(driver);
		assertNotEquals("cnbc.com samduck matsamduck", homePageCred.readCredList(driver, 0));

		//6. Test Delete Button
		homePageCred.addCredButtonClick(driver);
		homePageCred.addCred("bbc.com", "sammat","matsam", driver);
		driver.get("http://localhost:" + this.port + "/home");
		homePageCred.navCredTabClick(driver);
		assertNotEquals("bbc.com sammat matsam", homePageCred.readCredList(driver, 1));
		homePageCred.deleteCred();
		System.out.println("Credentials deleted");
		driver.get("http://localhost:" + this.port + "/home");
		homePageCred.navCredTabClick(driver);
		assertNotEquals("bbc.com sammat matsam", homePageCred.readCredList(driver, 0));

		//7. Logout
		homePageCred.logOut();

	}



}
