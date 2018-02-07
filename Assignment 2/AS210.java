/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package as210;
import java.util.*;
/**
 *
 * @author Hemant
 */
public class AS210 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.print("Enter N:");
        int n=scan.nextInt();
        ArrayList<Integer> residues=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if((i*j)%n==1){
                    residues.add(i);
                }
            }
        }
        HashSet<Integer> quadretic=new HashSet<>();
        for (int i = 0; i < residues.size(); i++) {
            quadretic.add((int)Math.pow(residues.get(i),2)%n);
        }
        residues.clear();
        residues.addAll(quadretic);
        Collections.sort(residues);
        System.out.println("Quadratic Residues are "+residues);
    }
    
}
