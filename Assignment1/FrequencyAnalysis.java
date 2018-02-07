/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencyanalysis;
import java.util.*;
import java.io.*;
/**
 *
 * @author MANISH BAIRAGI
 */
public class FrequencyAnalysis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        
	Scanner scan=new Scanner(System.in);
//	String str=scan.nextLine();
	String str="";
	File f=new File("H:\\CaeserCipher\\xyz.txt");
	ArrayList<Integer> ar=new ArrayList<>(Collections.nCopies(26, 0));
	FileInputStream is=new FileInputStream(f);
	int temp=0;
	System.out.println("");
	while((temp=is.read())!=-1){
            str+=(char)temp+"";
            if(Character.isLetter((char)temp)){
                int p=ar.get(temp-97);
                ar.remove(temp-97);
                ar.add(temp-97,p+1);
            }
	}
    //   	System.out.println(ar);
	
	File f1=new File("H:\\CaeserCipher\\frequency.txt");
	FileOutputStream os=new FileOutputStream(f1);	
        for (int i = 0; i < 26; i++) {
            int max=Collections.max(ar);
            int z=ar.indexOf(max);
            max=(char)(z+97)-101;
            ar.remove(z);
            
            for(int j=0;j<str.length();j++){
                if(Character.isLetter(str.charAt(j))){
                    if((str.charAt(j)-max)<97){
                        os.write((char)(str.charAt(j)-max+26));
                    }
                    else{
                        os.write((char)(str.charAt(j)-max));
                    }
                }
                else
                    os.write((char)(str.charAt(j)));
            }
            os.write('\n');
        }		
    }
}
