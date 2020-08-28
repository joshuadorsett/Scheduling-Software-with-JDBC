package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//  this is used to encapsulate all of the connection information needed
public class MakeConnection {
    private static final String DB="U07nke";
    private static final String URL="jdbc:mysql://3.227.166.251/"+DB;
    private static final String USERNAME="U07nke";
    private static final String PASSWORD="53689079763";
    private static final String DRIVER="com.mysql.cj.jdbc.Driver";
    private static Connection connection;

//    a method used to establish a connection with the database
    public static void makeConnection(){
        try {
            Class.forName(DRIVER);
            connection= (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection to "+ DB +" has been established");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

//    a method used to access the connection object
    public static Connection getConnection(){
        return connection;
    }

//    this method accesses the disconnect lambda expression.
    public static void endConnection() throws SQLException {
        connection.close();
        System.out.println("Connection to " + DB + " has ended");
    }
}
