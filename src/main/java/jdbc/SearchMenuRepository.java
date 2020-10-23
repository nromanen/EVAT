package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static utility.AzureConnection.getConnection;

public class SearchMenuRepository {
    public static int getNumberOfEventsByTitle(String keyword) throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet rs = statement.executeQuery("select * from [eventsexpress-test].[dbo].[events] where Title = '"+keyword+"'");
        List<String> titles = new ArrayList<>();
        while (rs.next()){
            String title=  rs.getString("Title");
            titles.add(title);
        }
        return titles.size();
    }


}
