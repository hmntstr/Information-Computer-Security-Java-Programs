/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hillcipher;
import java.io.*;
import java.util.*;
/**
 *
 * @author MANISH BAIRAGI
 */
public class HillCipher {
    
    static String matrixmultiplication(int[][] a,int[][] b) throws Exception{
        int c[][]=new int[3][1];
        String temp="";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                c[i][j]=0;
                for (int k = 0; k < 3; k++) {
                    c[i][j]=c[i][j]+a[i][k]*b[k][j];
                }
            //    os.write((char)((c[i][j])%26+97));
                temp+=((char)((c[i][j])%26+97))+"";
            }
        }
        return temp;
    }
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
	//str="ret";
        File f1=new File("C:\\Users\\MANISH BAIRAGI\\Desktop\\xyz.txt");
        FileOutputStream os=new FileOutputStream(f1);
        System.out.print("Enter 3*3 matrix Key:");
    //    String keys=scan.next().toLowerCase();
        int[][] key=new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                key[i][j]=scan.nextInt();//keys.charAt(i*3+j)-97;
            }
        }
        int[][] data=new int[3][1];
        String temps="";
        while(str.length()>=3){
            data[0][0]=str.charAt(0)-97;
            data[1][0]=str.charAt(1)-97;
            data[2][0]=str.charAt(2)-97;
            str=str.replace(str.substring(0, 3),"");
            
            temps=matrixmultiplication(key, data);
            for (int i = 0; i < temps.length(); i++) {
                os.write(temps.charAt(i));
            }
        }
        if(str.length()!=0){
            int r=3-str.length();
            for (int i = 0; i < str.length(); i++) {
                data[i][0]=str.charAt(i)-97;
            }
            for (int i = 0; i < r; i++) {
                data[i][0]='x'-97;
            }
            temps=matrixmultiplication(key, data);
            for (int i = 0; i < temps.length(); i++) {
                os.write(temps.charAt(i));
            }
        }
    }
}
