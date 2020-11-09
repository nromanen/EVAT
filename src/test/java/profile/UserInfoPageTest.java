package profile;

import jdbc.UserInfoRepository;
import org.testng.annotations.*;

import pages.profile.UserInfoPage;

import java.time.LocalDate;
import java.time.Period;
import java.util.Properties;

import static org.testng.Assert.*;

public class UserInfoPageTest {

    UserInfoPage userInfoPage;
    Properties prop;
    SetUpProfile setUpProfile;

    @BeforeClass
    public void setUp() {
        setUpProfile=new SetUpProfile();
        userInfoPage = new UserInfoPage(setUpProfile.getDriver());
        prop=setUpProfile.getProp();
    }

    @DataProvider
    public Object[][] providerEmail(){
        return new Object[][]{{prop.getProperty("email")}};
    }

    @Test(dataProvider = "providerEmail")
    public void testGetValueUserName(String email) {
        String name=UserInfoRepository.getColumnByEmail(email,"Name");
        if(name==null)name= email.substring(0,email.indexOf("@"));
        assertEquals(userInfoPage.getValueUserName(),name);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Test(dataProvider = "providerEmail")
    public void testGetValueAge(String email) {
        String age;
        LocalDate date=LocalDate.parse(UserInfoRepository.getColumnByEmail(email,"Birthday"));
        if(date.equals(LocalDate.parse("0001-01-01")))age="---";
        else age= Period.between(date,LocalDate.now()).getYears()+"";
        assertEquals(userInfoPage.getValueAge(),age);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(dataProvider = "providerEmail")
    public void testGetValueGender(String email) {
        String gender="";
        switch (Integer.parseInt(UserInfoRepository.getColumnByEmail(email,"Gender"))){
            case 0: gender="Other"; break;
            case 1: gender="Male"; break;
            case 2: gender="Female";
        }
        assertEquals(userInfoPage.getValueGender(),gender);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(dataProvider = "providerEmail")
    public void testGetValueEmail(String email) {
        assertEquals(userInfoPage.getValueEmail(),email);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Test(dataProvider = "providerEmail")
    public void testGetValueInterests(String email) {
        assertEquals(userInfoPage.getValueInterests(),UserInfoRepository.getUserInterests(email));
    }

    @AfterClass
    public void afterClass(){
        setUpProfile.driverQuit();
    }
}