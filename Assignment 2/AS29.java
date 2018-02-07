/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package as29;
import java.util.*;
/**
 *
 * @author Hemant
 */
public class AS29 {
    public static ArrayList<Integer> generator=new ArrayList<>();
    public static int fin=0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.print("Enter N:");
        int n=scan.nextInt();
        Property1(n);
        generators(n);
        if(!generator.isEmpty()){
            Property2(n);
            Property4();
        }
        
    }
    public static void Property1(int n){
        System.out.println("Property 1:");
        System.out.println("-----------");
        if(n==2 || n==4){
            System.out.println("Z"+n+"* has Generators.");
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
                System.out.println("Z"+reserve*n+"* has NO Generators.");
            }
            else{
                System.out.println(n+"="+reserve+"*"+res+"^"+(x-1));
                System.out.println("Z"+reserve*n+"* has Generators.");
            }
        }
    }
    public static void generators(int n){
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
        fin=euler;
        if(order.contains(euler)){
            System.out.println("Given Group is Cyclic Group."
                    + "\nTotal No. of Generators are "
                    + Collections.frequency(order, euler)
                    + "\nGenerators are ");
            for (int i = 0; i < residues.size(); i++) {
                if(order.get(i)==euler){
                    generator.add(residues.get(i));
                    System.out.print(residues.get(i)+" ");
                }
            }
            System.out.println("");
        }
        else{
            System.out.println("Given Group is Not Cyclic Group.");
        }
    }
    public static void Property2(int n) {
        System.out.println("Property 2:");
        System.out.println("-----------");
        System.out.print("Pick any Generator:");
        Scanner scan=new Scanner(System.in);
        int x=scan.nextInt();
        ArrayList<Integer> temp=new ArrayList<>();
         System.out.print("Z"+n+"*={");
        for (int i = 0; i <= fin-1; i++) {
            if(!temp.contains((int)Math.pow(x, i)%n)){
                temp.add((int)Math.pow(x, i)%n);
                System.out.print((int)Math.pow(x, i)%n+",");
            }
        }
        System.out.println("}");
        Property3(x,n);
    }
    public static void Property3(int x,int n) {
        System.out.println("Property 3:");
        System.out.println("-----------");
        for (int i = 2; i <= fin-1 ; i++) {
            if(generator.contains((int)Math.pow(x, i)%n)){
                System.out.println(x+"^"+i+"mod"+n+"="+(int)Math.pow(x, i)%n+" also a generator because gcd("+i+","+fin+")="+1);
                break;
            }
        }
    }
    public static void Property4() {
        System.out.println("Property 4:");
        System.out.println("-----------");
        int n=fin;
        ArrayList<Integer> residues=new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if(n%i==0){
                n/=i;
                residues.add(i);
            }
            if(n==1){
                break;
            }
        }
        double ans=fin;
        for (int i = 0; i < residues.size(); i++) {
            ans*=((double)1-((double)1/residues.get(i)));
        }
        System.out.println("Total No. of Generators are O("+fin+")="+(int)ans);
    }
}
