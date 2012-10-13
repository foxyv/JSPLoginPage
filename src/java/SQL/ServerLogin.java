package SQL;


import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    /*
     * Connection object for the database holding user information. Initialized
     * using ConnectToUserDB
     */
    Connection userDatabase;
    
    public static int USERAUTHENTICATED = 0;
    public static int USERNOTFOUND = 1;
    public static int BADPASS = 2;
    public static int USERALREADYEXISTS = 4;
    
    public static int SOMETHINGWENTWRONG = 10;
    
    private String DBUsername = "loginClient";
    private String DBPassword = "yCiVRarmXnJS6oJ";
    private String DBurlString = "jdbc:mysql://localhost:3306/userspace";
    
    
    MessageDigest SHA512; 
    

    //Connects to the user Database
    public void ConnectToUserDB() {
        try {
            //Initialize the properties of the connection
            Properties properties = new Properties();
            properties.put("user", this.DBUsername);
            properties.put("password", this.DBPassword);
            properties.put("characterEncoding", "ISO-8859-1");
            properties.put("useUnicode", "true");
            
            String url = "jdbc:mysql://localhost:3306/";

            //Create a new instance of the mysql JDBC driver;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            

            //Connect to the database at URL
            userDatabase = DriverManager.getConnection(url, properties);
            
            //Create our database if it doesn't already exist
            userDatabase.prepareCall("CREATE DATABASE IF NOT EXISTS loginusers;").execute();
            
            //Make sure our user table exists
            userDatabase.prepareCall("CREATE TABLE IF NOT EXISTS loginusers.loginUsers (username VARCHAR(50), password VARBINARY(100));").execute();
            
            

        } catch (Exception ex) {
            Logger.getLogger(ServerLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Initiate a login attempt. Returns USERAUTHENTICATED to indicate the credentials are valid.
    public int AttemptLogin(String username, String password) {

        try {
            if (userDatabase != null) {
                if (userDatabase.isValid(100));
            }
            
            //Use the SHA512 algorithm to produce a hash of the password in a byte array
            byte[] passwordAttemptDigested = MessageDigest.getInstance("SHA-512").digest(password.getBytes());
            
            //Creat the prepared statment for querying our user table
            PreparedStatement checkUsernamePasswordDigest = userDatabase.prepareCall("SELECT * FROM loginusers.loginusers where username='" + username + "';");
            
            
            //Get a result set form the executed query
            ResultSet results = checkUsernamePasswordDigest.executeQuery();

            //If there are any results go to the first
            if (results.first()) {
                //If the username is correct
                if (results.getString("username").equals(username)) {
                    //Check to see if the password hash in the resultSet matches what the user supplied
                    byte[] passwordHash = results.getBytes("password");
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
    
    /* Creates a user in the database table returns USERCREATED if successful, 
     * USEREXISTS if the user already exists or SOMETHINGWENTWRONG if a problem occurs.
     * 
     */
    public int CreateUser(String username, String password) throws Exception{
        int loginCheck = AttemptLogin(username,"");
        SHA512 = MessageDigest.getInstance("SHA-512");
        byte[] digestedPassword = SHA512.digest(password.getBytes());
        
        
        if(loginCheck != BADPASS){
                        
            PreparedStatement insertStatement = userDatabase.prepareCall("INSERT INTO loginusers.loginusers (username, password) VALUES(?,?)");
            insertStatement.setString(1,username);
            insertStatement.setBytes(2, digestedPassword);
            insertStatement.execute();
        }
        else throw new Exception("User Already Exists");
            
        
        
        return 0;
    }
}
