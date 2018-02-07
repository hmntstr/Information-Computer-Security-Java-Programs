/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package as22;
import java.util.*;
/**
 *
 * @author Hemant
 */
public class AS22 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.print("Enter N:");
        int n=scan.nextInt();
        ArrayList<Integer> elements=new ArrayList<>();
        elements.add(0);
        if(n==2 || n==4){
            for (int i = 0; i < n; i++) {
                for (int j = 1; j < n; j++) {
                    if((i*j)%n==1){
                        elements.add(i);
                        break;
                    }
                }
            }
            System.out.println("Order="+elements.size());
            System.out.println("Elements="+elements);
        }
        else{
            int reserve=1;
            if (n%2==0) {
                n=n/2;
                reserve=2;
            }
            int flag;
            ArrayList<Integer> ar=new ArrayList<>();
            for (int i = 3; i <= n; i+=2) {
                flag=0;
                for (int j = 2; j <= Math.sqrt(i); j++) {
                    if (i%j==0) {
                        flag=1;
                        break;
                    }
                }
                if (flag==0) {
                    ar.add(i);
                }
            }
            flag=0;
            int x=1,res=0;
            while(flag==0)
            {
                for (int i = 0; i < ar.size(); i++) {
                    if(Math.pow(ar.get(i),x)==n)
                    {
                        flag=1;
                        res=ar.get(i);
                        break;
                    }
                    else if(Math.pow(ar.get(i),x)>n){
                        ar.remove(i);
                    }
                }
                if(ar.isEmpty()){
                    break;
                }
                x++;
            }
            if(ar.isEmpty()){
                System.out.println("We cannot find G("+reserve*n+").");
            }
            else{
                System.out.println(n*reserve+"="+reserve+"*"+res+"^"+(x-1));
                for (int i = 0; i < n; i++) {
                    for (int j = 1; j < n; j++) {
                        if((i*j)%n==1){
                            elements.add(i);
                            break;
                        }
                    }
                }
                System.out.println("Order="+elements.size());
                System.out.println("Elements="+elements);
            }
        }
    }
    
}
