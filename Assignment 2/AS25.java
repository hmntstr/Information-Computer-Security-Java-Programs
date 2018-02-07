/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package as25;
import java.util.*;
/**
 *
 * @author Hemant
 */
public class AS25 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.print("Enter m and b:");
        int G=scan.nextInt();
        int N=scan.nextInt();
        int a1[]={0,1,0,G,0,1,N};
        System.out.println("Q A1 A2 A3 B1 B2 B3");
        System.out.println("- "+a1[1]+" "+a1[2]+" "+a1[3]+" "+a1[4]+" "+a1[5]+" "+a1[6]);
        int a2[]=new int[7];
        while(a1[6]!=1 && a1[6]!=0){
            a2[0]=a1[3]/a1[6];
            a2[1]=a1[4];
            a2[2]=a1[5];
            a2[3]=a1[6];
            a2[4]=a1[1]-(a2[0]*a1[4]);
            a2[5]=a1[2]-(a2[0]*a1[5]);
            a2[6]=a1[3]-(a2[0]*a1[6]);
            System.out.println(a2[0]+" "+a2[1]+" "+a2[2]+" "+a2[3]+" "+a2[4]+" "+a2[5]+" "+a2[6]);
            a1=a2.clone();
        }
        System.out.print("GF("+G+","+N+")=");
        while(a1[5]<0)
            a1[5]+=G;
        if(a1[6]==1)
            System.out.println(a1[5]);
        else
            System.out.println("Cannot Find");
    }
}
