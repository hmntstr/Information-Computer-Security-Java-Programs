/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autokey;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

/**
 *
 * @author MANISH BAIRAGI
 */
public class AutoKey {

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
    //    str="defendtheeastwallofthecastle";
	File f1=new File("C:\\Users\\MANISH BAIRAGI\\Desktop\\xyz.txt");
        System.out.print("Key:");
        String key=scan.next().toLowerCase();
        key+=str.substring(0,str.length()-key.length());
        String newkey=key;
        System.out.println(newkey);
        String mat[]=new String[26];
        mat[0]=new String();
        String alpha="abcdefghijklmnopqrstuvwxyz";
        mat[0]=alpha;
        for (int i = 0; i < 25; i++) {
            mat[i+1]=new String();
            String temps=alpha.substring(0,i+1);
            mat[i+1]=alpha.replace(temps, "")+temps;
        }
        for (int i = 0; i < str.length(); i++) {
            System.out.print(mat[str.charAt(i)-97].charAt(newkey.charAt(i)-97));
        }
    }
    
}
