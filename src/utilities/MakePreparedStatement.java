package utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//  this creates and encapsulates the prepared statement used in the DAO
public class MakePreparedStatement {


    private static PreparedStatement preparedStatement;


    public static void makePreparedStatement(Connection connection, String sqlStatement){
        try {
            preparedStatement = connection.prepareStatement(sqlStatement);
            System.out.println("A prepared statement object has been created");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


//      this returns the preparedStatement object
    public static PreparedStatement getPreparedStatement(){
        return preparedStatement;
    }


}
