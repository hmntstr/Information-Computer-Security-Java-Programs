/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playfaird;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

/**
 *
 * @author MANISH BAIRAGI
 */
public class PlayfairD {

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
	//	str+="";
	File f1=new File("C:\\Users\\MANISH BAIRAGI\\Desktop\\abcp.txt");
        System.out.print("Enter Key:");
        String key1=scan.next().toLowerCase();
        String key="";
        for (int i = 0; i < key1.length(); i++) {
            if(!key.contains(key1.charAt(i)+"")){
                key+=key1.charAt(i);
            }
        }
        
        String mat[]=new String[5];
        for (int i = 0; i < 5; i++) {
            mat[i]=new String();
        }
        
        int j=0,k=0,l=0;
        String temps="";
        while(l!=key.length()){
            temps+=key.charAt(l);
            if(temps.length()==5){
                mat[k]=temps;
                k++;
                temps="";
            }
            l++;
        }
        
        for (int i = 0; i < 26; i++) {
            if(!key.contains((char)(i+97)+"")){
                {
                    if((char)(i+97)=='j'){
                        i++;
                    }
                    if(temps.length()==5){
                        mat[k]=temps;
                        k++;
                        temps="";
                    }
                    temps+=(char)(i+97);
                }
            }
        }
        mat[k]=temps;
        
        for (int i = 0; i < 5; i++) {
            System.out.println(mat[i]);
        }
        
	FileOutputStream os=new FileOutputStream(f1);
	for(int i=0;i<str.length();i++){
            String ca="",cb="";
            ca=str.charAt(i)+"";
            i++;
            if(i<str.length()){
                cb=str.charAt(i)+""; 
            }
            else{
                cb="x";
            }
            if(ca.equals(cb))
            {
                cb="x";
                i--;
            }
            
            int x1=0,y1=0,x2=0,y2=0;
            for (int m= 0; m < 5; m++) {
                if(mat[m].contains(ca)){
                    y1=mat[m].indexOf(ca);
                    x1=m;
                    break;
                }
            }
            for (int m = 0; m < 5; m++) {
                if(mat[m].contains(cb)){
                    y2=mat[m].indexOf(cb);
                    x2=m;
                    break;
                }
            }
            if(x1==x2 && y1!=y2){
                os.write(mat[x1].charAt((y1+4)%5));
                os.write(mat[x1].charAt((y2+4)%5));
            }
            else if(x1!=x2 && y1==y2){
                os.write(mat[(x1+4)%5].charAt(y1));
                os.write(mat[(x2+4)%5].charAt(y1));
            }
            else{
                os.write(mat[x1].charAt(y2));
                os.write(mat[x2].charAt(y1));
            }
	}
    }
    
}
