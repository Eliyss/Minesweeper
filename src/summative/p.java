/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summative;

/**
 *
 * @author User
 */
public class p {
    public static void t (String x) {
        System.out.println(x);
    }
    
    public static void r (String x) {
        System.out.print(x);
    }
    
    public static void w (String x) {
        System.out.println(x);
        try {
            Thread.sleep(500);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

    }
}
