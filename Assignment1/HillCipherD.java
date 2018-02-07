/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hillcipherd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

/**
 *
 * @author MANISH BAIRAGI
 */
public class HillCipherD {
    
    static int modInverse(int a, int m)
    {
        a = a%m;
        int x;
        for (x=1; x<m; x++)
            if ((a*x) % m == 1){
                break;
            }
        return x;
    }
    
    static int[][] transpose(int a[][],int m){
        int b[][] = new int[m][m];
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
                b[i][j] = a[j][i];
            }
        }
        return b;
    }
    
    // defining function to calculat inverse of a matrix
    static int[][] inverse(int a[][],int m,int det){
        int output[][] = new int[m][m];
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
                output[i][j] = (a[i][j])%26;
                if(output[i][j]<0){
                    output[i][j]+=26;
                }
                output[i][j]*=det;
                output[i][j]%=26;
            }
        }
        return output;
    }
    
    // defining function to calculate adjacent matrix
    static int[][] adjacent(int a[][],int m){
        int output[][] = new int[m][m];
        int det = 0,s=0,t=0;
        for (int z=0;z<m;z++){
            for (int i=0;i<m;i++){
                int b[][] = new int[m-1][m-1];
                for(int j=0;j<m;j++){
                    if(j!=z){
                        for(int k=0;k<m;k++){
                            if(k!=i){
                                //System.out.println(i+" "+j+" "+k+" "+s+" "+t);
                                b[s][t] = a[j][k];    
                                t++;
                            }
                        }
                        s++;
                    }
                    t = 0;
                }
                s = 0;
                /*for(int x=0;x<m-1;x++){
                    for(int y=0;y<m-1;y++){
                        System.out.print(b[x][y]+" ");
                    }
                    System.out.println();
                }*/
                //System.out.println(determinant(b,m-1)+" "+Math.pow(-1,i+z)+" "+a[z][i]);
                output[z][i] = (int)Math.pow(-1,i+z)*determinant(b,m-1);
            } 
        }
        return output;
    }
    
    // defining function to calculate determinant of a matrix of order of M
    static int determinant(int a[][],int m){
        if(m==1){
            return a[0][0];
        }
        else if(m==2){
            return a[0][0]*a[1][1]-a[0][1]*a[1][0];
        }
        else{
            int det = 0,s=0,t=0;
            for (int i=0;i<m;i++){
                int b[][] = new int[m-1][m-1];
                for(int j=0;j<m;j++){
                    if(j!=0){
                        for(int k=0;k<m;k++){
                            if(k!=i){
                                //System.out.println(i+" "+j+" "+k+" "+s+" "+t);
                                b[s][t] = a[j][k];    
                                t++;
                            }
                        }
                        s++;
                    }
                    t = 0;
                }
                s = 0;
                det = det+(int)Math.pow(-1,i)*a[0][i]*determinant(b,m-1);
            } 
            return det;
        }
    }
    
    
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
    
    static float[][] matrixinverse(float[][] mat) throws Exception{
        float det = 0;
        for(int i = 0; i < 3; i++)
	    det = det + (mat[0][i] * (mat[1][(i+1)%3] * mat[2][(i+2)%3] - mat[1][(i+2)%3] * mat[2][(i+1)%3]));
		
            System.out.println("\ndeterminant = " + det);
				
            System.out.println("\nInverse of matrix is:");
            for(int i = 0; i < 3; ++i) {
		for(int j = 0; j < 3; ++j)
                    System.out.print((((mat[(j+1)%3][(i+1)%3] * mat[(j+2)%3][(i+2)%3]) - (mat[(j+1)%3][(i+2)%3] * mat[(j+2)%3][(i+1)%3]))/ det) + " ");
			
                    System.out.print("\n");
		}
        return mat;
    }
    
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
	//str="ret";
        File f1=new File("C:\\Users\\MANISH BAIRAGI\\Desktop\\abcp.txt");
        FileOutputStream os=new FileOutputStream(f1);
        System.out.print("Enter 3*3 matrix Key:");
    //    String keys=scan.next().toLowerCase();
        int[][] key=new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                key[i][j]=scan.nextInt();//keys.charAt(i*3+j)-97;
            }
        }
         double det = determinant(key,3);         // calling determinant function
        System.out.println("Determinant "+det);
        if(det==0){
            System.out.println("Inverse is not possible");
            System.exit(0);
        }
        // printing transpose matrix
        int transpose_matrix[][] = transpose(key,3);
        
        // printing adjacent matrix
        int adjacent_matrix[][] = adjacent(transpose_matrix,3);
        
        // printing inverse matrix
        System.out.println("Inverse Matrix:");
        int inverse_matrix[][] = inverse(adjacent_matrix,3,modInverse((int)det%26, 26));
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(inverse_matrix[i][j]+" ");
            }
            System.out.println("");
        }
        
        int[][] data=new int[3][1];
        String temps="";
        while(str.length()>=3){
            data[0][0]=str.charAt(0)-97;
            data[1][0]=str.charAt(1)-97;
            data[2][0]=str.charAt(2)-97;
            str=str.replace(str.substring(0, 3),"");
            
            temps=matrixmultiplication(inverse_matrix, data);
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
            temps=matrixmultiplication(inverse_matrix, data);
            for (int i = 0; i < temps.length(); i++) {
                os.write(temps.charAt(i));
            }
        }
    }
}

