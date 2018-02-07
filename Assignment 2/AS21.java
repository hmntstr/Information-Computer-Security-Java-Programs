/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package as21;
import java.util.*;
/**
 *
 * @author Hemant
 */
public class AS21 {

    /**
     * @param args the command line arguments
     */
    static long mypow( long base, long pow, long mod ){
        if( pow == 0 ) return 1;
        if( pow % 2 == 0 ){
            long tmp = mypow( base, pow >> 1, mod );
            return tmp * tmp % mod;
        }
        else{
            return base * mypow( base, pow - 1, mod ) % mod;
        }
    }
    
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.print("Enter a, x and n:");
        long a=scan.nextLong();
        long x=scan.nextLong();
        long n=scan.nextLong();
        System.out.println("a^x mod n="+mypow(a, x, n));
    }
}
