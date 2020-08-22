package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MakeConnection {
    private static final String DB="U07nke";
    private static final String URL="jdbc:mysql://3.227.166.251/"+DB;
    private static final String USERNAME="U07nke";
    private static final String PASSWORD="53689079763";
    private static final String DRIVER="com.mysql.cj.jdbc.Driver";
    private static Connection connection;


    public static void makeConnection(){
        try {
            Class.forName(DRIVER);
            connection= (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection to "+ DB +" has been established");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Connection getConnection(){
        return connection;
    }

    public static void endConnection() {
        try {
            connection.close();
            System.out.println("Connection to " + DB + " has ended");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
