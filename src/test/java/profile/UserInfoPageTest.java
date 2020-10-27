package profile;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.profile.UserInfoPage;
import utility.AzureConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAmount;

import static org.testng.Assert.*;

public class UserInfoPageTest {

    UserInfoPage userInfoPage;

    @BeforeTest
    public void setUp() {
        new SetUpProfile();
        SetUpProfile.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#main > div.mt-2 > header > div > div > div > div")));
        userInfoPage = new UserInfoPage(SetUpProfile.getDriver());
    }

    @DataProvider
    public Object[][] providerGetValueUserName(){
        String name = null;
        try(Connection connection =AzureConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select [Users].Name from [Users] where [Users].Email like '"+userInfoPage.getValueEmail()+"'")) {
            if (rs.next()) {
                name=rs.getString("Name");
                if(name==null)name= userInfoPage.getValueEmail().substring(0,userInfoPage.getValueEmail().indexOf("@"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return new Object[][]{{name}};
    }

    @Test(dependsOnMethods ="testGetValueEmail", dataProvider = "providerGetValueUserName")
    public void testGetValueUserName(String name) {
        assertEquals(userInfoPage.getValueUserName(),name);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] providerGetValueAge(){
        String age = null;
        try(Connection connection =AzureConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select [Users].Birthday from [Users] where [Users].Email like '"+userInfoPage.getValueEmail()+"'")) {
            if (rs.next()) {
                LocalDate date=LocalDate.parse(rs.getString("Birthday"));
                if(date.equals(LocalDate.parse("0001-01-01")))age="---";
                else age= Period.between(date,LocalDate.now()).getYears()+"";
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return new Object[][]{{age}};
    }

    @Test(dependsOnMethods ="testGetValueEmail", dataProvider = "providerGetValueAge")
    public void testGetValueAge(String age) {
        assertEquals(userInfoPage.getValueAge(),age);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] providerGetValueGender(){
        String gender = null;
        try(Connection connection =AzureConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select [Users].Gender from [Users] where [Users].Email like '"+userInfoPage.getValueEmail()+"'")) {
            if (rs.next()) {
                switch (rs.getInt("Gender")){
                    case 0: gender="Other"; break;
                    case 1: gender="Male"; break;
                    case 2: gender="Female";
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return new Object[][]{{gender}};
    }

    @Test(dependsOnMethods ="testGetValueEmail", dataProvider = "providerGetValueGender")
    public void testGetValueGender(String gender) {
        assertEquals(userInfoPage.getValueGender(),gender);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testGetValueEmail() {
        try(Connection connection =AzureConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select [Users].Email from [Users] where [Users].Email like '"+userInfoPage.getValueEmail()+"'")) {
            if (rs.next()) {
                assertEquals(rs.getString("Email"),userInfoPage.getValueEmail());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] providerValueInterests(){
        StringBuilder interests = new StringBuilder();
        try(Connection connection =AzureConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select [dbo].[Categories].[Name] " +
                    "from  [dbo].[Users] join [dbo].[UserCategory] " +
                    "on [dbo].[Users].Id=[dbo].[UserCategory].UserId " +
                    "join [dbo].[Categories] " +
                    "on [dbo].[Categories].Id=[dbo].[UserCategory].CategoryId " +
                    "wHERE [dbo].[Users].[Email] LIKE '"+userInfoPage.getValueEmail()+"'")) {
            while (rs.next()) {
                interests.append("#"+rs.getString("Name")+"\n");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        interests.deleteCharAt(interests.lastIndexOf("\n"));
        return new Object[][]{{interests.toString()}};
    }

    @Test(dependsOnMethods ="testGetValueEmail", dataProvider = "providerValueInterests")
    public void testGetValueInterests(String interests) {
        assertEquals(userInfoPage.getValueInterests(),interests);
    }
}