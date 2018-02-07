/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasiski;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 *
 * @author MANISH BAIRAGI
 */
public class Kasiski {

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
        temp=0;
        String[] substr=new String[1000];
        int count[]=new int[1000];
        for (int i = 1; i <str.length()/2 ; i++) {
            for (int j = 0; j < str.length()-i+1; j++) {
            //    int flag=0;
                for (int k = j+i; k < str.length()-i+1; k++) {
                    String str1=str.substring(j,j+i);
                    String str2=str.substring(k,k+i);
                    if(str1.equals(str2)){
                        count[temp]=k-j;
                        substr[temp]=str1;
                        temp++;
                //        flag=1;
                //        break;
                    }
                }
            //    if(flag==1){
            //        break;
            //    }
            }
        }
        int max=0,index=0;
        for (int i = 0; i <temp; i++) {
            System.out.println(substr[i]+" "+count[i]);
        }
        HashSet<Integer> hs=new HashSet<>();
        for (int i = 0; i < temp; i++) {
            hs.add(count[i]);
        }
        for (Integer I : hs) {
            int countt=0;
            for (int i = 0; i < temp; i++) {
                if(I==count[i]){
                    countt++;
                }
            }
            if(countt>max){
                max=countt;
                index=I;
            }
        }
        System.out.print("\nKey can be ");
        for (int i = 1; i <= index; i++) {
            if (index%i==0) {
                System.out.print(i+" ");
            }
        }
    }
}
