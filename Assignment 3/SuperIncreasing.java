/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superincreasing;
import java.util.*;
/**
 *
 * @author MANISH BAIRAGI
 */
public class SuperIncreasing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Enter total no. of Elements:");
        Scanner scan=new Scanner(System.in);
        int n=scan.nextInt();
        if(n<=6){
        System.out.println("Enter Elements:");
        int a[]=new int[n];
        for (int i = 0; i < n; i++) {
            a[i]=scan.nextInt();
        }
        int sum=0;
        boolean flag=true;
        for (int i = 0; i < n; i++) {
            if(!(a[i]>sum)){
                flag=false;
                break;
            }
            sum+=a[i];
        }
        if(flag==true)
            System.out.println("Given Sequence is SuperIncreasing");
        else
            System.out.println("Given Sequence is Not SuperIncreasing");
        }
        else
            System.out.println("Maximum 6 Elements Only");
    }
    
}
