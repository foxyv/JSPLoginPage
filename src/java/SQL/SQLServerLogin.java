package SQL;

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
public class SQLServerLogin {

    /*
     * Connection object for the database holding user information. Initialized
     * using ConnectToUserDB
     */
    Connection userDatabase;
    public static int USERAUTHENTICATED = 0;
    public static int USERNOTFOUND = 1;
    public static int BADPASS = 2;
    public static int USERALREADYEXISTS = 4;
    public static int INVALIDUSERNAME = 5;
    public static int USERCREATED = 6;
    public static int SOMETHINGWENTWRONG = 10;
    private String DBUsername = "loginClient";
    private String DBPassword = "yCiVRarmXnJS6oJ";
    private String DBurlString = "jdbc:mysql://localhost:3306/";

    public String getDBPassword() {
        return DBPassword;
    }

    public void setDBPassword(String DBPassword) {
        this.DBPassword = DBPassword;
    }

    public String getDBUsername() {
        return DBUsername;
    }

    public void setDBUsername(String DBUsername) {
        this.DBUsername = DBUsername;
    }

    public String getDBurlString() {
        return DBurlString;
    }

    public void setDBurlString(String DBurlString) {
        this.DBurlString = DBurlString;
    }
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


            //Create a new instance of the mysql JDBC driver;
            Class.forName("com.mysql.jdbc.Driver").newInstance();


            //Connect to the database at URL
            userDatabase = DriverManager.getConnection(DBurlString, properties);

            //Create our database if it doesn't already exist
            userDatabase.prepareCall("CREATE DATABASE IF NOT EXISTS loginusers;").execute();

            //Make sure our user table exists
            userDatabase.prepareCall("CREATE TABLE IF NOT EXISTS loginusers.loginUsers (username VARCHAR(50), password VARBINARY(100));").execute();

            //Make sure our userinfo table exists
            userDatabase.prepareCall("CREATE TABLE IF NOT EXISTS loginusers.loginUsersInfo (firstname VARCHAR(50),username VARCHAR(50), email VARCHAR(100),occupation VARCHAR(100), age INT(1) UNSIGNED);").execute();


        } catch (Exception ex) {
            Logger.getLogger(SQLServerLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Initiate a login attempt. Returns USERAUTHENTICATED to indicate the credentials are valid.
    public int ValidateLogin(String username, String password) {

        try {

            //TODO: add username string validation (No special characters etc)

            if (userDatabase != null) {
                if (!userDatabase.isValid(100)) {
                    return SOMETHINGWENTWRONG;
                }
            }

            //Use the SHA512 algorithm to produce a hash of the password in a byte array
            byte[] passwordAttemptDigested = MessageDigest.getInstance("SHA-512").digest(password.getBytes());


            //Creat the prepared statment for querying our user table (Make sure all input is sanitized)
            PreparedStatement checkUsernamePasswordDigest = userDatabase.prepareCall("SELECT * FROM loginusers.loginusers where username=?;");
            checkUsernamePasswordDigest.setString(1, username);


            //Get a result set form the executed query
            ResultSet results = checkUsernamePasswordDigest.executeQuery();


            //If there are any results go to the first otherwise return USERNOTFOUND
            if (results.first()) {
                //Check the username.
                if (results.getString("username").equals(username)) {
                    //Check to see if the password hash in the resultSet matches what the user supplied
                    byte[] passwordHash = results.getBytes("password");
                    if (Arrays.equals(passwordHash, passwordAttemptDigested)) {
                        return USERAUTHENTICATED;
                    } else {
                        return BADPASS;
                    }
                } else {
                    return USERNOTFOUND;
                }
            } else {
                return USERNOTFOUND;
            }


        } catch (Exception ex) {
            Logger.getLogger(SQLServerLogin.class.getName()).log(Level.SEVERE, null, ex);
            return SOMETHINGWENTWRONG;
        }

    }

    /*
     * Creates a user in the database table returns USERCREATED if successful,
     * USEREXISTS if the user already exists or SOMETHINGWENTWRONG if a problem
     * occurs.
     *
     */
    public int CreateUser(String username, String password) throws Exception {
        int loginCheck = ValidateLogin(username, "");
        SHA512 = MessageDigest.getInstance("SHA-512");
        byte[] digestedPassword = SHA512.digest(password.getBytes());


        if (loginCheck != BADPASS) {

            PreparedStatement insertStatement = userDatabase.prepareCall("INSERT INTO loginusers.loginusers (username, password) VALUES(?,?)");
            insertStatement.setString(1, username);
            insertStatement.setBytes(2, digestedPassword);
            insertStatement.execute();
        } else if (loginCheck == BADPASS) {
            return USERALREADYEXISTS;
        }




        return USERCREATED;
    }

    public UserBean makeAuthenticatedUserBean(String username, String password) {
        UserBean retrievedBean = new UserBean();
        if (ValidateLogin(username, password) == USERAUTHENTICATED) {
            try {
                //Create the prepared statment for querying our user table (Make sure all input is sanitized)
                PreparedStatement retrievalQuery = userDatabase.prepareCall("SELECT * FROM loginusers.loginusersinfo where username=?;");
                retrievalQuery.setString(1, username);

                //Get the user info into a result set
                ResultSet userInfo = retrievalQuery.executeQuery();

                //Set the bean to authenticated and assign a username
                retrievedBean.authenticated = "Authenticated";
                retrievedBean.username = username;

                //If there is any information in the table for that user, use it to populate the bean
                if (userInfo.first()) {
                    retrievedBean.age = userInfo.getInt("age");
                    retrievedBean.email = userInfo.getString("email");
                    retrievedBean.occupation = userInfo.getString("occupation");
                    retrievedBean.firstname = userInfo.getString("firstname");
                }

                updateUserBean(retrievedBean);

            } catch (SQLException ex) {
                Logger.getLogger(SQLServerLogin.class.getName()).log(Level.SEVERE, null, ex);
            }


        } else {
            Logger.getLogger(SQLServerLogin.class.getName()).log(Level.SEVERE, null, "Bad Username or Password");

        }

        return retrievedBean;
    }

    public boolean updateUserBean(UserBean updateMe) {
        
                System.out.println(updateMe.firstname);
                System.out.println(updateMe.email);
                System.out.println(updateMe.occupation);
                System.out.println(updateMe.age);
                System.out.println(updateMe.username);
                System.out.println(updateMe.authenticated);
                
        if (updateMe.authenticated.equals("Authenticated")) {
            
            try {
                //Check to see if an entry for the user already exists in the loginusersinfo table
                PreparedStatement retrievalQuery = userDatabase.prepareCall("SELECT * FROM loginusers.loginusersinfo where username=?;");
                retrievalQuery.setString(1, updateMe.username);

                //Get the user info into a result set
                ResultSet userInfo = retrievalQuery.executeQuery();

                
                //If there is any information in the table for that user, update that entry
                if (userInfo.first()) {
                    System.out.println("Updating login entry");
                    PreparedStatement updateStatement = userDatabase.prepareCall("UPDATE loginusers.loginusersinfo SET age=?, email=?, occupation=?, firstname=? WHERE username=?;");
                    updateStatement.setInt(1, updateMe.getAge());
                    updateStatement.setString(2, updateMe.email);
                    updateStatement.setString(3, updateMe.occupation);
                    updateStatement.setString(4, updateMe.firstname);
                    updateStatement.setString(5, updateMe.username);
                    System.out.println(updateStatement);
                    updateStatement.execute();

                    return true;

                } else {
                    System.out.println("New login entry");
                    //Otherwise create a new entry
                    PreparedStatement insertStatement = userDatabase.prepareCall("INSERT INTO loginusers.loginusersinfo (username, age, email, occupation, firstname) VALUES(?,?,?,?,?);");
                    insertStatement.setString(1, updateMe.username);
                    insertStatement.setInt(2, updateMe.age);
                    insertStatement.setString(3, updateMe.email);
                    insertStatement.setString(4, updateMe.occupation);
                    insertStatement.setString(5, updateMe.firstname);
                    insertStatement.execute();
                    return true;

                }


            } catch (SQLException ex) {
                Logger.getLogger(SQLServerLogin.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
            }
        }
        return false;
    }
}
