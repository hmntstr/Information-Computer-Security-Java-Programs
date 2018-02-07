/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package as24;
import java.util.*;
/**
 *
 * @author Hemant
 */
public class AS24 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.print("Enter N:");
        int n=scan.nextInt();
        HashSet<Integer> hs=new HashSet<>();
        while(n%2==0){
            hs.add(2);
            n/=2;
        }
        for (int i = 3; i <= Math.sqrt(n); i+=2) {
            while(n%i==0){
                hs.add(i);
                n/=i;
            }
        }
        if(n!=0){
            hs.add(n);
        }
        long mul=1;
        for (Integer h : hs) {
            mul*=h-1;
        }
        System.out.println("Euler's Totient Function Value="
                + mul);
    }
}
