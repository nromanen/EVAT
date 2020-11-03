package jdbc;

import utility.AzureConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface UserInfoRepository {

    static String getInfoByEmail(String email, String column){
        String res = null;
        try(Connection connection = AzureConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select [Users]."+column+" from [Users] where [Users].Email like '"+email+"'")) {
            if (rs.next()) {
                res=rs.getString(column);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    static String getUserInterests(String email){
        StringBuilder interests = new StringBuilder();
        try(Connection connection =AzureConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select [dbo].[Categories].[Name] " +
                    "from  [dbo].[Users] join [dbo].[UserCategory] " +
                    "on [dbo].[Users].Id=[dbo].[UserCategory].UserId " +
                    "join [dbo].[Categories] " +
                    "on [dbo].[Categories].Id=[dbo].[UserCategory].CategoryId " +
                    "wHERE [dbo].[Users].[Email] LIKE '"+email+"'")) {
            while (rs.next()) {
                interests.append("#").append(rs.getString("Name")).append("\n");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(interests.length()!=0)interests.deleteCharAt(interests.lastIndexOf("\n"));
        return interests.toString();
    }
}
