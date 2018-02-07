/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veginered;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

/**
 *
 * @author MANISH BAIRAGI
 */
public class VeginereD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        Scanner scan=new Scanner(System.in);
	//	String str=scan.nextLine();
        String str="";
	File f=new File("C:\\Users\\MANISH BAIRAGI\\Desktop\\xyz.txt");
	FileInputStream is=new FileInputStream(f);
	int temp=0;
	while((temp=is.read())!=-1){
            if(Character.isLetter((char)temp))
		str=str+Character.toLowerCase((char)temp)+"";
	}
    //    str="t";
	File f1=new File("C:\\Users\\MANISH BAIRAGI\\Desktop\\abcp.txt");
        FileOutputStream os=new FileOutputStream(f1);
        System.out.print("Key:");
        String key=scan.next().toLowerCase();
        String newkey="";
        temp=str.length()/key.length()+1;
        for (int i = 0; i < temp; i++) {
            newkey+=key;
        }
        String mat[]=new String[27];
        mat[0]=new String();
        String alpha="abcdefghijklmnopqrstuvwxyz";
        mat[0]=alpha;
        for (int i = 0; i < 26; i++) {
            mat[i+1]=new String();
            String temps=alpha.substring(0,i+1);
            mat[i+1]=alpha.replace(temps, "")+temps;
            System.out.println(mat[i]);
        }
        System.out.println(mat[26]);
        for (int i = 0; i < str.length(); i++) {
            System.out.print((char)(mat[newkey.charAt(i)-97].indexOf(str.charAt(i)+"")+97));
        }
    }
    
}
