package jdbc;

import utility.AzureConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface EventsRepository {

    static int getAmountOfCountries(){
        int res=-1;
        try(Connection connection = AzureConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select count([dbo].[Countries].Name) from [dbo].[Countries]")) {
            if(rs.next())
                res=rs.getInt(1);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    static int getAmountOfCities(String country){
        int res=-1;
        try(Connection connection = AzureConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select count([dbo].Cities.Name) " +
                    "from [dbo].Countries JOIN  [dbo].[Cities] " +
                    "ON  [dbo].Countries.Id=[dbo].[Cities].CountryId " +
                    "WHERE [dbo].Countries.Name LIKE '"+country+"'")) {
            if(rs.next()) res=rs.getInt(1);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    static int getAmountOfEvents(){
        int res=-1;
        try(Connection connection = AzureConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select count(*) from [dbo].[Events]")) {
            if(rs.next())
                res=rs.getInt(1);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    static void deleteEvents(String title,String description, String ownerId){
        try(Connection connection = AzureConnection.getConnection();
            Statement statement = connection.createStatement()){
            statement.executeQuery("delete from dbo.Events " +
                    "where Events.Title like '"+title+"' and Events.Description like '"+description+
                    "' and Events.OwnerId like '"+ownerId+"'");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
