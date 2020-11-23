package base;

<<<<<<< HEAD
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
=======
>>>>>>> master
import org.testng.annotations.BeforeClass;
import pages.EventInfoPage;
import pages.SignInUpMenu;
import pages.navigation.EditProfilePage;
import pages.navigation.EventsNotificationPage;
import pages.search.HomePageSearchMenu;

public class SignInBaseTest extends BaseTest {

	protected EditProfilePage editProfilePage;
	protected EventsNotificationPage eventsNotificationPage;
	protected EventInfoPage eventInfoPage;
	protected String imagePath = "C:\\resources\\img.jpg";
	protected String comment = "auto generated comment";
	protected String replyOnComment = "auto generated reply on comment";
	private String email = "d.bozhevilnyi@gmail.com";
	private String pass = "131089";

	@BeforeClass
	@Override
	public void setUp() {
		super.setUp();
<<<<<<< HEAD
		openBrowser();
=======

>>>>>>> master
		driver.get(HomePageSearchMenu.URL);
		editProfilePage = new EditProfilePage(getDriver());
		eventsNotificationPage = new EventsNotificationPage(getDriver());
		eventInfoPage = new EventInfoPage(getDriver());
		signIn();
	}

	private void signIn() {
		new SignInUpMenu(driver).authoriseUser(email, pass);
	}
	
	@AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenshot(result);
    }
}
