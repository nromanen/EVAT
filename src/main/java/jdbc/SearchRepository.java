package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import static utility.AzureConnection.getConnection;

public class SearchRepository {
    public static int getNumberOfFoundedEvents(String query) {
        int numb = 0;
        try(Statement statement = getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query)){
        while (rs.next()) {
            numb = rs.getInt(1);
        }
        } catch (SQLException e){
            e.printStackTrace();
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

    public static String getLocalDateNow(){
        LocalDate date = LocalDate.now();
        String month = String.valueOf(date.getMonthValue());
        String day;
        if (date.getDayOfMonth() >= 10){
            day = String.valueOf(date.getDayOfMonth());
        } else {
            day = "0"+String.valueOf(date.getDayOfMonth());
        }
        int year = date.getYear();
        return month + "/" + day + "/" + year;
    }

    public static int getNumberOfEventsByOneHashtag(String hashtag) {
        LocalDate date = LocalDate.now();
        String query = "select COUNT(Name) from (select TOP(6) Name from [Events] JOIN [EventCategory] ON Events.Id = EventCategory.EventId JOIN [Categories] ON Categories.Id = EventCategory.CategoryId where Name = '" + hashtag + "' and DateFrom >= '" + date + "' AND IsBlocked = 'false') a";
        return getNumberOfFoundedEvents(query);
    }

    public static int getNumberOfEventsByTwoHashtags(String hashtag1, String hashtag2) {
        LocalDate date = LocalDate.now();
        String query = "select COUNT(CardId) from (select DISTINCT TOP(6) Events.Id AS CardId from [Events] JOIN [EventCategory] ON Events.Id = EventCategory.EventId JOIN [Categories] ON Categories.Id = EventCategory.CategoryId where Name = '" + hashtag1 + "' and DateFrom >= '" + date + "' or Name = '" + hashtag2 + "' and DateFrom >= '" + date + "' AND IsBlocked = 'false') a";
        return getNumberOfFoundedEvents(query);
    }

    public static int getNumberOfEventsWithKeywordWithNInQueryAndDateFrom(String keyword, LocalDate date) {
        String query = "select COUNT(Id) from [Events] where Title LIKE N'%" + keyword + "%' OR Description LIKE N'%" + keyword + "%' AND DateFrom >= '" + date + "' AND IsBlocked = 'false'";
        return getNumberOfFoundedEvents(query);
    }

    public static int getNumberOfEventsByKeywordAndHashtag(String keyword, String hashtag) {
        LocalDate date = LocalDate.now();
        String query = "select COUNT(Name) from (select TOP(6) Name from [Events] JOIN [EventCategory] ON Events.Id = EventCategory.EventId JOIN [Categories] ON Categories.Id = EventCategory.CategoryId WHERE Title LIKE N'%" + keyword + "%' OR Description LIKE N'%" + keyword + "%' AND Name = '" + hashtag + "' and DateFrom >= '" + date + "' AND IsBlocked = 'false') a";
        return getNumberOfFoundedEvents(query);
    }

    public static int getNumberOfEventsByDatesAndHashtag(LocalDate date1, LocalDate date2, String hashtag) {
        String query = "select COUNT(Name) from (select TOP(6) Name from [Events] JOIN [EventCategory] ON Events.Id = EventCategory.EventId JOIN [Categories] ON Categories.Id = EventCategory.CategoryId WHERE DateFrom >= '" + date1 + "' AND DateTo <= '" + date2 + "'AND Name = '" + hashtag + "' AND IsBlocked = 'false') a";
        return getNumberOfFoundedEvents(query);
    }

    public static int getNumberOfEventsByKeywordWithNInQueryAndDateFromAndHashtag(String keyword, LocalDate date, String hashtag) {
        String query = "select COUNT(Name) from (select TOP(6) Name from [Events] JOIN [EventCategory] ON Events.Id = EventCategory.EventId JOIN [Categories] ON Categories.Id = EventCategory.CategoryId WHERE Title LIKE N'%" + keyword + "%' OR Description LIKE N'%" + keyword + "%' AND Name = '" + hashtag + "' and DateFrom >= '" + date + "' AND IsBlocked = 'false') a";
        return getNumberOfFoundedEvents(query);
    }
    public static void addNewHashtag(String hashtagId, String hashtagName){
        try(Statement statement = getConnection().createStatement()){
            statement.executeUpdate("INSERT INTO [eventsexpress-test].dbo.Categories (Id, Name) VALUES('"+hashtagId+"', '"+hashtagName+"')");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void deleteHashtag(String hashtagId){
        try(Statement statement = getConnection().createStatement()){
            statement.executeUpdate("DELETE FROM [eventsexpress-test].dbo.Categories WHERE Id = '"+hashtagId+"'");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static int getNumberOfUsersByNameWithNInQuery(String userName){
        String query = "SELECT COUNT(Id) FROM [eventsexpress-test].dbo.Users WHERE Name = N'"+userName+"'";
        return getNumberOfFoundedEvents(query);
    }
    public static int getNumberOfAllEvents() throws SQLException {
        LocalDate date = LocalDate.now();
        String query = "select COUNT(Id) from (select TOP(6) * from [Events] where DateFrom >= '" + date + "' AND IsBlocked = 'false') a";
        return getNumberOfFoundedEvents(query);
    }
//    public static void main(String[] args) {
//        deleteHashtag("38F785E3-B43E-46FF-3788-08D85413B488");
//    }
}
