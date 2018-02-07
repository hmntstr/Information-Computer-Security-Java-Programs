/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package as23;

import java.util.*;

/**
 *
 * @author Hemant
 */
public class AS23 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.print("Enter Your choice(1)2 byte integer(2)4 byte integer(3)8 byte integer:");
        int ch=scan.nextInt();
        switch(ch){
            case 1:{System.out.print("Enter N:");
                    short n=scan.nextShort();
                    HashSet<Short> hs=new HashSet<>();
                    hs.add((short)1);
                    while(n%2==0){
                        hs.add((short)2);
                        n/=(short)2;
                    }
                    
                    for (short i = 3; i <= Math.sqrt(n); i+=2) {
                        while(n%i==0){
                            hs.add(i);
                            n/=i;
                        }
                    }
                    if(n!=0){
                        hs.add(n);
                    }
                    System.out.println(hs);}
                    break;
            case 2:{System.out.print("Enter N:");
                    int n=scan.nextInt();
                    HashSet<Integer> hs=new HashSet<>();
                    hs.add(1);
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
                    System.out.println(hs);}
                    break;
            case 3:{System.out.print("Enter N:");
                    long n=scan.nextLong();
                    HashSet<Long> hs=new HashSet<>();
                    hs.add((long)1);
                    while(n%2==0){
                        hs.add((long)2);
                        n/=2;
                    }
                    for (long i = 3; i <= Math.sqrt(n); i+=2) {
                        while(n%i==0){
                            hs.add(i);
                            n/=i;                            
                        }
                    }
                    if(n!=0){
                        hs.add(n);
                    }
                    System.out.println(hs);}
                    break;
            default:System.out.println("Enter Valid Choice.");
        }
    }
}
