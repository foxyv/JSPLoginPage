/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sweord
 */
public class ServerLoginDriver {

    public static void main(String[] args) {

        SQLServerLogin rawr = new SQLServerLogin();
        rawr.ConnectToUserDB();

        try {
            rawr.CreateUser("aflack", "aack");
        } catch (Exception ex) {
            Logger.getLogger(ServerLoginDriver.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (rawr.ValidateLogin("aflack", "aack") == SQLServerLogin.USERAUTHENTICATED) {
            System.out.println("Success!");
        }
        else System.out.println("Login Failed!");
    }
}
