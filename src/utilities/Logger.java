package utilities;

import dao.UserDaoImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
//  this class has one method that logs user data at login in the log.txt file
public class Logger {
    private static final String path = "log.txt";
    public static void logger(boolean success) {
        try (FileWriter fileWriter = new FileWriter(path, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)){
            String userName = UserDaoImpl.getActiveUser().getUserName();
            String  userId = Integer.toString(UserDaoImpl.getActiveUser().getUserId());
            String userInfo = ", User Name: " + userName + ", ID: " + userId;
            String logInSuccess = ZonedDateTime.now() + userInfo + ",  Login Attempt: Successful.";
            String logInFailure = ZonedDateTime.now() + userInfo + ",  Login Attempt: Unsuccessful.";
            if (success) {
                printWriter.println(logInSuccess);
                System.out.println(logInSuccess);
            } else {
                printWriter.println(logInFailure);
                System.out.println(logInFailure);
            }
        }catch (IOException throwables){
            throwables.printStackTrace();
        }
    }
}

