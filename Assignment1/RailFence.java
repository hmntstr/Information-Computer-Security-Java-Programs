/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railfence;
import java.io.*;
import java.util.*;
/**
 *
 * @author MANISH BAIRAGI
 */
public class RailFence {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        Scanner scan=new Scanner(System.in);
	//	String str=scan.nextLine();
        String str="";
	File f=new File("C:\\Users\\MANISH BAIRAGI\\Desktop\\abc.txt");
	FileInputStream is=new FileInputStream(f);
	int temp=0;
	while((temp=is.read())!=-1){
            if(Character.isLetter((char)temp))
		str=str+Character.toLowerCase((char)temp)+"";
	}
	File f1=new File("C:\\Users\\MANISH BAIRAGI\\Desktop\\xyz.txt");
        System.out.print("Key:");
        String key=scan.next();
        char[][] ans=new char[str.length()/key.length()+1][key.length()];
        int line=0,j=0;
        for (int i = 0; i < str.length(); i++) {
            ans[line][j]=str.charAt(i);
            j++;
            if(j>=key.length()){
                j=0;
                line++;
            }
        }
        
        char[][] enc=new char[ans.length][ans[0].length];
        for (int i = 0; i < ans.length; i++) {
            for (int k = 0; k < key.length(); k++) {
                enc[i][k]=ans[key.charAt(k)-49][i];
            }
        }
        
        System.out.println("Encryption");
        for (int i = 0; i < enc.length; i++) {
            for (int k = 0; k < enc[0].length; k++) {
                System.out.print(enc[i][k]);
            }
            System.out.println("");
        }
        
        System.out.println("\n\n\nDecryption");
        for (int i = 0; i < enc.length; i++) {
            for (int k = 0; k < key.length(); k++) {
                System.out.print(enc[k][key.indexOf((i+1)+"")]);
            }
        }
    }
    
}
