/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital_Database;

/**
 *
 * @author mistakx
 */

public class ClearConsole {

    public static void clearConsole() {

        // for (int i = 0; i < 50; i++) {
        //     System.out.println("\n");
        // }

        System.out.print("\033[H\033[2J");
        System.out.flush();


    }

};
