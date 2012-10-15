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
            UserBean rawr2 = rawr.makeAuthenticatedUserBean("aflack", "aack");
            
            rawr2.age = 27;
            rawr2.firstname = "Verna";
            rawr2.email = "thevernabean@gmail.com";
            rawr2.occupation = "Software Developer";
            
            System.out.println(rawr.updateUserBean(rawr2));
            
            System.out.println(rawr2.firstname);
            System.out.println(rawr2.email);
            System.out.println(rawr2.occupation);
            System.out.println(rawr2.age);
            
        } catch (Exception ex) {
            Logger.getLogger(ServerLoginDriver.class.getName()).log(Level.SEVERE,
                    null, ex);
        }



    }
}
