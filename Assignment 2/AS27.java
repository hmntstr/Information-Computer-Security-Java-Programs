/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package as27;

import java.util.Scanner;

/**
 *
 * @author Hemant
 */
public class AS27 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.print("Enter a, x and n:");
        long a=scan.nextLong();
        long x=scan.nextLong();
        long n=scan.nextLong();
        int flag=1;
        for (int i = 2; i <=Math.sqrt(n) ; i++) {
            if(n%i==0){
                flag=0;
                break;
            }
        }
        if(flag==1){
            x%=(n-1);
            a%=n;
            System.out.println("a^x mod n="+(long)Math.pow(a, x)%n);
        }
        else{
            System.out.println("Cannot use Fermet's Little Theorem");
        }
    }
    
}
