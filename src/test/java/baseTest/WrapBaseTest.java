package baseTest;

import org.testng.annotations.BeforeTest;

import pages.EventInfoPage;
import pages.SignInUpMenu;
import pages.homePageSearch.HomePageSearchMenu;
import pages.navBar.EditProfilePage;
import pages.navBar.EventsNotificationPage;

public class WrapBaseTest extends BaseTest {

	protected EditProfilePage editProfilePage;
	protected EventsNotificationPage eventsNotificationPage;
	protected EventInfoPage eventInfoPage;
	protected String imagePath = "C:\\resources\\img.jpg";
	protected String comment = "auto generated comment";
	protected String replyOnComment = "auto generated reply on comment";
	private String email = "d.bozhevilnyi@gmail.com";
	private String pass = "131089";

	@BeforeTest
	@Override
	public void setUp() {
		super.setUp();
		driver.get(HomePageSearchMenu.URL);
		editProfilePage = new EditProfilePage(getDriver());
		eventsNotificationPage = new EventsNotificationPage(getDriver());
		eventInfoPage = new EventInfoPage(getDriver());
		signIn();
	}

	private void signIn() {
		new SignInUpMenu(driver).authoriseUser(email, pass);
	}
}
