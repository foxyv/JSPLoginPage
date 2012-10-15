/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sweord
 */
public class SQLQueryTest {

    public Connection openConnection() {
        try {
            
            Properties properties = new Properties();
            properties.put("user", "vernabean");
            properties.put("password", "!@Oe68vM3Y5ccd");
            properties.put("characterEncoding", "ISO-8859-1");
            properties.put("useUnicode", "true");
            String url = "jdbc:mysql://localhost:3306/userspace";

            Class.forName("come.mysql.jdbc.Driver").newInstance();
            
            Connection aConnection = DriverManager.getConnection(url, properties);
            
            aConnection.prepareStatement("");
            return aConnection;

        } catch (Exception ex) {
            Logger.getLogger(SQLQueryTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
