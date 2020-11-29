package profile;

import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.profile.UserInfoPage;

public class UserInfoPageTest extends ProfileBaseTest{

    UserInfoPage userInfoPage;

    @DataProvider
    public Object[][] providerEmail(){
        return new Object[][]{
                {getDataByKey("email"),getDataByKey("password"),getDataByKey("userName"),
                        getDataByKey("age"),getDataByKey("gender"),getDataByKey("interests")},
                {getDataByKey("email2"),getDataByKey("password2"),getDataByKey("userName2"),
                        getDataByKey("age2"),getDataByKey("gender2"),getDataByKey("interests2")}};
    }


    /**
     * Verify information on the page profile
     * @param email
     * @param password
     * @param userName
     * @param age
     * @param gender
     * @param interests
     */
    @Description("Verify information on the page profile")
    @Test(dataProvider = "providerEmail")
    public void testGetValueUserName(String email,String password, String userName, String age,
                                     String gender, String interests) {
        signingIn(email,password);
        goToProfilePage();
        userInfoPage = new UserInfoPage(getDriver());
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(userInfoPage.getValueUserName(),userName,"Incorrect userName on page");
        softAssert.assertEquals(userInfoPage.getValueAge(),age,"Incorrect age on page");
        softAssert.assertEquals(userInfoPage.getValueGender(),gender,"Incorrect gender on page");
        softAssert.assertEquals(userInfoPage.getValueEmail(),email,"Incorrect email on page");
        softAssert.assertEquals(userInfoPage.getValueInterests(),interests,"Incorrect interests on page");
        softAssert.assertAll();
    }


}