/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package as28;
import java.util.*;
/**
 *
 * @author Hemant
 */
public class AS28 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.print("Enter N:");
        int n=scan.nextInt();
        ArrayList<Integer> residues=new ArrayList<>();
        ArrayList<Integer> order=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if((i*j)%n==1){
                    residues.add(i);
                    int flag=0;
                    for (int k = 1; k < n; k++) {
                        if((long)Math.pow(i, k)%n==1){
                            order.add(k);
                            flag=1;
                            break;
                        }
                    }
                    if(flag==0){
                        order.add(0);
                    }
                    break;
                }
            }
        }
        
        int euler=residues.size();
        if(order.contains(euler)){
            System.out.println("Given Group is Cyclic Group."
                    + "\nTotal No. of Generators are "
                    + Collections.frequency(order, euler)
                    + "\nGenerators are ");
            for (int i = 0; i < residues.size(); i++) {
                if(order.get(i)==euler){
                    System.out.print(residues.get(i)+" ");
                }
            }
            System.out.println("");
        }
        else{
            System.out.println("Given Group is Not Cyclic Group.");
        }
    }
    
}
