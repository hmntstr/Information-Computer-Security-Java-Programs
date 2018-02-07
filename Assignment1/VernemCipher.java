/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vernemcipher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

/**
 *
 * @author MANISH BAIRAGI
 */
public class VernemCipher {

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
		str=str+(char)temp+"";
	}
	//	str+="";
	File f1=new File("C:\\Users\\MANISH BAIRAGI\\Desktop\\xyz.txt");
	FileOutputStream os=new FileOutputStream(f1);
        
        System.out.println("Enter Key:");
        String key=scan.next();
        String out="";
        for (int i = 0; i < str.length(); i++) {
            out+=(char)((str.charAt(i)^key.charAt(i%key.length()))%26+97);
        }
        for(int i=0;i<str.length();i++){
            os.write((char)out.charAt(i));
	}
    }
    
}
