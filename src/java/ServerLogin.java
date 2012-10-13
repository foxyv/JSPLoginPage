
import java.security.MessageDigest;
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Sweord
 */
public class ServerLogin {

    Connection userDatabase;
    
    private static int USERAUTHENTICATED = 0;
    public static int USERNOTFOUND = 1;
    private static int BADPASS = 2;
    private static int USERALREADYEXISTS = 4;
    
    public static int SOMETHINGWENTWRONG = 10;

    public void ConnectToUserDB() {
        try {
            Properties properties = new Properties();
            properties.put("user", "loginClient");
            properties.put("password", "asfd564f");
            properties.put("characterEncoding", "ISO-8859-1");
            properties.put("useUnicode", "true");
            String url = "jdbc:mysql://localhost:3306/userspace";

            Class.forName("come.mysql.jdbc.Driver").newInstance();

            userDatabase = DriverManager.getConnection(url, properties);

        } catch (Exception ex) {
            Logger.getLogger(ServerLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DisconnectFromUserDB() throws SQLException {
        userDatabase.close();


    }

    public int AttemptLogin(String username, String password) {

        try {
            if (userDatabase != null) {
                if (userDatabase.isValid(100));
            }
            byte[] passwordAttemptDigested = MessageDigest.getInstance("SHA-512").digest(password.getBytes());
            PreparedStatement checkUsernamePasswordDigest = userDatabase.prepareCall("SELECT * FROM users where username=" + username);
            ResultSet results = checkUsernamePasswordDigest.executeQuery();

            if (results.first()) {
                if (results.getString("username").equals(username)) {
                    byte[] passwordHash = results.getBytes("passwordHash");
                    if(Arrays.equals(passwordHash, passwordAttemptDigested)) return USERAUTHENTICATED;
                    else return BADPASS;
                }
                else return USERNOTFOUND;
            } else {
                return USERNOTFOUND;
            }


        } catch (Exception ex) {
            Logger.getLogger(ServerLogin.class.getName()).log(Level.SEVERE, null, ex);
            return SOMETHINGWENTWRONG;
        }
        
    }
    
    public int CreateUser(String username, String password){
        
        return 0;
    }
}
