/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caesercipher;
import java.util.*;
import java.io.*;
/**
 *
 * @author MANISH BAIRAGI
 */
public class CaeserCipher {

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
	for(int i=0;i<str.length();i++){
            char c=str.charAt(i);
            if(Character.isLetter(c))
		if(c<=119)
                    os.write((char)(str.charAt(i)+3));
		else
                    os.write((char)(str.charAt(i)+3-26));
            else	
		os.write((char)str.charAt(i));
	}		
    }
}
