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
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    public static Connection getConnection(){
        return connection;
    }

    /*
    A lambda expression for disconnecting the connection that returns in the endConnection Interface.
     */
    public static Disconnect disconnect = () -> {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    };
    public static void endConnection() {
        disconnect.disconnect();
        System.out.println("Connection to " + DB + " has ended");
    }
}
