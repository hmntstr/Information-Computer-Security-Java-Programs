/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polyguscipher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

/**
 *
 * @author MANISH BAIRAGI
 */
public class PolygusCipher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
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
	//	str+="";
	File f1=new File("C:\\Users\\MANISH BAIRAGI\\Desktop\\xyz.txt");
        System.out.print("Enter 25 character Key(i/j):");
        String key=scan.next().toLowerCase();
        key="abcdefghiklmnopqrstuvwxyz";
        String mat[]=new String[5];
        for (int i = 0; i < 5; i++) {
            mat[i]=new String();
        }
        
        for (int i = 0; i < 5; i++) {
            mat[i]=key.substring(i*5,i*5+5);
            System.out.println(mat[i]);
        }
        FileOutputStream os=new FileOutputStream(f1);
        String str1="";
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < 5; j++) {
                if (mat[j].contains(str.charAt(i)+"")) {
                    str1+=((char)(j+48));
                    str1+=((char)(mat[j].indexOf(str.charAt(i))+48));
                }
            }
        }
        System.out.println(str1);
        for (int i = 0; i < str1.length(); i++) {
          //  System.out.println((str1.charAt(i)-48)+" "+(str1.charAt(i+1)-48));
            System.out.print((mat[str1.charAt(i)-48].charAt(str1.charAt(i+1)-48)));
            i++;
            //System.out.println((mat[str1.charAt(i)-48].charAt(str1.charAt(i+1)-48)));
        }
    }
}
