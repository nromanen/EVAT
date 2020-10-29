package jdbc;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.homePageSearch.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import static utility.AzureConnection.getConnection;

public class SearchRepository {
    public static int getNumberOfFoundedEvents(String query) throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        int numb = 0;
        while (rs.next()) {
            numb = rs.getInt(1);
        }
        return numb;
    }

    public static int getNumberOfEventsWithKeywordInTitleOrDescriptionWithNInQuery(String keyword) throws SQLException {
        String query = "select COUNT(Id) from (select TOP(6) * from [Events] where Title LIKE N'%" + keyword + "%' OR Description LIKE N'%" + keyword + "%' AND IsBlocked = 'false') a";
        return getNumberOfFoundedEvents(query);
    }

    public static int getNumberOfEventsWithTwoDates(LocalDate date1, LocalDate date2) throws SQLException {
        String query = "select COUNT(Id) from (select TOP(6) * from [Events] where DateFrom >= '" + date1 + "' AND DateTo <= '" + date2 + "' AND IsBlocked = 'false') a";
        return getNumberOfFoundedEvents(query);
    }

    public static int getNumberOfEventsWithDateFrom(LocalDate date) throws SQLException {
        String query = "select COUNT(Id) from (select TOP(6) * from [Events] where DateFrom >= '" + date + "' AND IsBlocked = 'false') a";
        return getNumberOfFoundedEvents(query);
    }

    public static int getNumberOfEventsFromNowTillDateTo(LocalDate date) throws SQLException {
        LocalDate date1 = LocalDate.now();
        String query = "select COUNT(Id) from (select TOP(6) * from [Events] where DateFrom >= '" + date1 + "' AND DateTo <= '" + date + "' AND IsBlocked = 'false') a";
        return getNumberOfFoundedEvents(query);
    }

    public static String getLocalDateNow() {
        LocalDate date = LocalDate.now();
        return date.getMonthValue() + "/" + date.getDayOfMonth() + "/" + date.getYear();
    }

    public static int getNumberOfEventsByOneHashtag(String hashtag) throws SQLException {
        LocalDate date = LocalDate.now();
        String query = "select COUNT(Name) from (select TOP(6) Name from [Events] JOIN [EventCategory] ON Events.Id = EventCategory.EventId JOIN [Categories] ON Categories.Id = EventCategory.CategoryId where Name = '" + hashtag + "' and DateFrom >= '" + date + "' AND IsBlocked = 'false') a";
        return getNumberOfFoundedEvents(query);
    }

    public static int getNumberOfEventsByTwoHashtags(String hashtag1, String hashtag2) throws SQLException {
        LocalDate date = LocalDate.now();
        String query = "select COUNT(CardId) from (select DISTINCT TOP(6) Events.Id AS CardId from [Events] JOIN [EventCategory] ON Events.Id = EventCategory.EventId JOIN [Categories] ON Categories.Id = EventCategory.CategoryId where Name = '" + hashtag1 + "' and DateFrom >= '" + date + "' or Name = '" + hashtag2 + "' and DateFrom >= '" + date + "' AND IsBlocked = 'false') a";
        return getNumberOfFoundedEvents(query);
    }

    public static int getNumberOfEventsWithKeywordWithNInQueryAndDateFrom(String keyword, LocalDate date) throws SQLException {
        String query = "select COUNT(Id) from [Events] where Title LIKE N'%" + keyword + "%' OR Description LIKE N'%" + keyword + "%' AND DateFrom >= '" + date + "' AND IsBlocked = 'false'";
        return getNumberOfFoundedEvents(query);
    }

    public static int getNumberOfEventsByKeywordAndHashtag(String keyword, String hashtag) throws SQLException {
        LocalDate date = LocalDate.now();
        String query = "select COUNT(Name) from (select TOP(6) Name from [Events] JOIN [EventCategory] ON Events.Id = EventCategory.EventId JOIN [Categories] ON Categories.Id = EventCategory.CategoryId WHERE Title LIKE N'%" + keyword + "%' OR Description LIKE N'%" + keyword + "%' AND Name = '" + hashtag + "' and DateFrom >= '" + date + "' AND IsBlocked = 'false') a";
        return getNumberOfFoundedEvents(query);
    }

    public static int getNumberOfEventsByDatesAndHashtag(LocalDate date1, LocalDate date2, String hashtag) throws SQLException {
        String query = "select COUNT(Name) from (select TOP(6) Name from [Events] JOIN [EventCategory] ON Events.Id = EventCategory.EventId JOIN [Categories] ON Categories.Id = EventCategory.CategoryId WHERE DateFrom >= '" + date1 + "' AND DateTo <= '" + date2 + "'AND Name = '" + hashtag + "' AND IsBlocked = 'false') a";
        return getNumberOfFoundedEvents(query);
    }

    public static int getNumberOfEventsByKeywordWithNInQueryAndDateFromAndHashtag(String keyword, LocalDate date, String hashtag) throws SQLException {
        String query = "select COUNT(Name) from (select TOP(6) Name from [Events] JOIN [EventCategory] ON Events.Id = EventCategory.EventId JOIN [Categories] ON Categories.Id = EventCategory.CategoryId WHERE Title LIKE N'%" + keyword + "%' OR Description LIKE N'%" + keyword + "%' AND Name = '" + hashtag + "' and DateFrom >= '" + date + "' AND IsBlocked = 'false') a";
        return getNumberOfFoundedEvents(query);
    }

}
