/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package merkel.hellman;

import java.nio.charset.Charset;
import java.util.*;
import java.math.BigInteger;

/**
 *
 * @author MANISH BAIRAGI
 */
public class MerkelHellman {

    private LinkedList wList;
        
        /**
         * Constructor
         * @param wInt
         */
        MerkelHellman(int wInt[]){
                wList = new LinkedList();
                for (int i = 0; i < wInt.length; i++) {
                        wList.add(wInt[i]);
                }
        }
        
        /**
         * @param pubKey
         * @param plainText
         * @return cipher text string
         */
        String MHEncrypt(MHPublicKey pubKey, String plainText){
                String plainTextBin, cipherText;
                LinkedList b;
                Iterator bIterator;
                int cipherInt, idx;

                System.out.println("\n---------Encryption-----------");
                System.out.println("Public Key is : " + pubKey.getB().toString());
                System.out.println("User inputs contains " + plainText.length() + " characters");
                b = pubKey.getB();
                plainTextBin = "";
                cipherText = "";
                cipherInt = 0;

                for(int i=0; i<plainText.length(); i++ ){
                        plainTextBin = charToBinary(plainText.charAt(i));

                        cipherInt = 0;
                        bIterator = b.iterator();
                        idx = 0;
                        while(bIterator.hasNext()){
                                cipherInt += Integer.parseInt(bIterator.next().toString()) * Integer.parseInt(Character.toString(plainTextBin.charAt(idx)));
                                idx ++;
                        }
                        
                        if(cipherText.equals("")){
                                cipherText = Integer.toString(cipherInt);
                        }else{
                                cipherText = cipherText + ' ' + Integer.toString(cipherInt);
                        }
                        
                }       
                
                return cipherText;
        }
        
        /**
         * @param privKey
         * @param cipherText
         * @return plain text string
         */
        String MHDecrypt(MHPrivateKey privKey, String cipherText){
                String plainText, plainTextBin;
                String[] cipherTextArray;
                BigInteger rModInverse, plainTextBI,tmp;
                LinkedList w, plainBinList;
                Iterator wIterator, pIterator;
                
                System.out.println("\n---------Decryption-----------");
                rModInverse = privKey.getR().modInverse(privKey.getQ());
                System.out.println("RModInverse is : " + rModInverse);
                w = privKey.getW();
                System.out.println("Private Key is: " + "W = " + w + "; Q = " + privKey.getQ().toString() + "; R = " + privKey.getR().toString());
                
                plainText = "";
                plainTextBin = "";
                cipherTextArray = cipherText.split(" ");
                for(int i=0; i<cipherTextArray.length; i++){
                        plainTextBin = "";
                        cipherText = cipherTextArray[i];
                        
                        //Calculate plainTextBI = cipherText * rModInverse mod q
                        plainTextBI = (new BigInteger(cipherText)).multiply(rModInverse).mod(privKey.getQ());
                        
                        plainBinList = new LinkedList();
                        wIterator = w.descendingIterator();//reverse w, move large number to head 
                        
                        while(wIterator.hasNext()){
                                tmp = plainTextBI.subtract(new BigInteger(wIterator.next().toString()));
                                if(tmp.compareTo(new BigInteger("0")) < 0 ){
                                        plainBinList.addFirst(0);
                                }else{
                                        plainBinList.addFirst(1);
                                        plainTextBI = tmp;
                                }
                        }
                        
                        pIterator = plainBinList.iterator();
                        while(pIterator.hasNext()){
                                plainTextBin += pIterator.next().toString();
                        }
                        plainText = plainText + Character.toString(binaryToChar(plainTextBin));
                }
                
                return plainText;
        }
        
        /**
         * @param ch a character which will be transferred to binary
         * @return binary code of the input character
         */
        String charToBinary(char ch){
                String chBin = Integer.toBinaryString((int)ch);
                chBin = String.format("%8s", chBin).replace(' ', '0');
                
                return chBin;
        } 
        
        /**
         * @param binStr the binary string
         * @return ASCII character of the binary string
         */
        char binaryToChar(String binStr){
                char[] temp = binStr.toCharArray();
        int sum = 0;  
        for (int i = 0; i < temp.length; i++) {
            sum += (Integer.parseInt(Character.toString(temp[i])) << (temp.length - i - 1)); 
        }  
      
        return (char) sum;  
        }
        
        /**
         * @return W
         */
        LinkedList getW(){
                return wList;
        }

        /**
         * @param args
         */
        public static void main(String[] args) {
                LinkedList wList;
                String plainText, cipherText;
                MHKey key;
                BigInteger q, r;
                MerkelHellman mhTest;
                
                //create the super increasing set of numbers
                int wInt[] = { 2, 7, 11, 21, 42, 89, 180, 354 };
                
                mhTest = new MerkelHellman(wInt);
                wList = mhTest.getW();
                key = new MHKey(wList);
                
                if(args.length == 0){
                        plainText = "Welcome to Data Structures and Algorithms";
                }else{
                        plainText = args[0];
                }
                
                cipherText = mhTest.MHEncrypt(key.getPubKey(), plainText);
                System.out.println("Cipher Text is : " + cipherText);
                
                plainText = mhTest.MHDecrypt(key.getPrivKey(), cipherText);
                System.out.println("Plain Text is : " + plainText);
        }
}

/**
 * Class MHKey contains both public and private key for MerkleHellman algorithm
 * @author songluo
 *
 */
class MHKey{
        MHPrivateKey privKey;
        MHPublicKey pubKey;
        
        MHKey(){
                privKey = new MHPrivateKey();
                pubKey = new MHPublicKey();
        }
        
        
        MHKey(LinkedList w){
                generateKey(w);
        }
        
        /**
         * Generate privateKey and pubKey base on super increasing list w
         * @param w
         */
        void generateKey(LinkedList w){
                BigInteger wSum, q, r;
                LinkedList b;
                Iterator wIterator;
                Random rand;
                int wSumBitLen, qLen;
                
                System.out.println("w is : " + w.toString());
                wSum = new BigInteger("0");
                wIterator = w.iterator();
                //Get the SUM of each node of w
                while(wIterator.hasNext()){
                        wSum = wSum.add(new BigInteger(wIterator.next().toString()));
                }
                System.out.println("wSum is : " + wSum.toString());
                
                System.out.println("\n---------Generate q, which should be greater than wSum------------");
                wSumBitLen = wSum.bitLength();
                rand = new Random();
                q = new BigInteger(wSumBitLen, rand);
                while(q.compareTo(wSum) <= 0){
                        //System.out.println("Fail - q is :" + q.toString());
                        q = new BigInteger(wSumBitLen, rand);
                }
                System.out.println("Success - q is :" + q.toString());
                
                System.out.println("\n---------Generate r, to make gcd(r,q) = 1------------");
                qLen = q.bitLength();
                r = new BigInteger(qLen, rand);
                while(Integer.parseInt(r.gcd(q).toString()) != 1){
                        System.out.println("Fail - r is :" + r.toString());
                        r = new BigInteger(qLen, rand);
                }
                System.out.println("Success - r is :" + r.toString());
                
                System.out.println("\n---------Generate b, b. = rw. mod q, which is the public key------------");
                b = new LinkedList();
                wIterator = w.iterator();
                while(wIterator.hasNext()){
                        b.add(r.multiply(new BigInteger(wIterator.next().toString())).mod(q));
                }
                System.out.println("b is : " + b.toString());
                
                privKey = new MHPrivateKey(w, q, r);
                pubKey = new MHPublicKey(b);
        }
        
        /**
         * @return privKey
         */
        MHPrivateKey getPrivKey(){
                return privKey;
        }
        
        /**
         * @return pubKey
         */
        MHPublicKey getPubKey(){
                return pubKey;
        }
}

/**
 * @author songluo
 *
 */
class MHPrivateKey{
        private LinkedList w;
        private BigInteger q;
        private BigInteger r;
        
        MHPrivateKey(){
                this(null, null, null);
        }
        
        MHPrivateKey(LinkedList w, BigInteger q, BigInteger r){
                this.w = w;
                this.q = q;
                this.r = r;
        }
        
        LinkedList getW(){
                return w;
        }
        
        BigInteger getQ(){
                return q;
        }
        
        BigInteger getR(){
                return r;
        }
        
        
}

/**
 * @author songluo
 *
 */
class MHPublicKey{
        private LinkedList b;
        
        MHPublicKey(){
                this(null);
        }
        
        MHPublicKey(LinkedList b){
                this.b = b;
        }
        
        LinkedList getB(){
                return b;
        }
}