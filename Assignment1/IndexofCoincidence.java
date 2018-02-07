/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indexofcoincidence;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 *
 * @author MANISH BAIRAGI
 */
public class IndexofCoincidence {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
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
        int count[]=new int[26];
        String str1=str;
        double sum=0;
        for (int i = 0; i < 26; i++) {
            count[i] = str1.length() - str1.replace((char)(i+97)+"", "").length();
            sum+=(count[i]*(count[i]-1));
        }
        sum/=str.length()*str.length()-1;
        System.out.println("Index of Coincidence="+sum);
        System.out.print("Type of Cipher is ");
        if(Math.abs(sum-0.038)<Math.abs(sum-0.065)){
            System.out.println("Polyalphabetic Cipher");
        }
        else{
            System.out.println("Monoalphabetic Cipher");
        }
        sum=0.035;
        double keylength=(0.027*str.length())/Math.abs(((str.length()-1)*sum)+0.065-(0.038*str.length()));
        System.out.println("KeyLength="+keylength);
    }
}
