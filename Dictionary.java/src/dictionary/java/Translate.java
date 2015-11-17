/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dictionary.java;

/**
 *
 * @author Anh
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class Translate {

    int n =0;
    public Translate() throws IOException {
    }
    
    
     final int MAX = 1000;
    String[][] dictionary = new String[MAX][2];
       public void docFileAnh() throws FileNotFoundException, IOException {
             for(int i =0; i < MAX; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                dictionary[i][j]="";
            }
        }
        FileReader fr = new FileReader("anh.txt");
        BufferedReader br = new BufferedReader(fr);
        String line ="";
        int countLine = 0;
        
        while ((line = br.readLine()) != null)
        {
            line = line.replace("\ufeff", "");
            for(int i = 0; i <line.length(); i++)
            {
                
                if(line.charAt(i) == ':'){
                    for(int j = i+1; j < line.length();j++){
                        dictionary[countLine][1] += line.charAt(j);
                    }
                    break;
                }
                else{
                    dictionary[countLine][0] += line.charAt(i);
                }
            }
            countLine++;
        }
        br.close();
        n =countLine;
    }
       
       public void docFileViet() throws FileNotFoundException, IOException {
             for(int i =0; i < MAX; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                dictionary[i][j]="";
            }
        }
        FileReader fr = new FileReader("viet.txt");
        BufferedReader br = new BufferedReader(fr);
        String line ="";
        int countLine = 0;
        while ((line = br.readLine()) != null)
        {
            
            line = line.replace("\ufeff", "");
            for(int i = 0; i < line.length(); i++)
            {
                
                if(line.charAt(i) == ':'){
                    for(int j = i+1; j < line.length();j++){
                        dictionary[countLine][0] += line.charAt(j);
                    }
                    break;
                }
                else{
                    dictionary[countLine][1] += line.charAt(i);
                }
            }
            countLine++;
           
        }
        br.close();
        n =countLine;
    }
       
       public void setEnglish(String en, int i){
           dictionary[i][0] = en;
       }
       
       public void setVietNam(String vi, int i){
           dictionary[i][1] = vi;
       }
       public String getEnglish(int i){
           return dictionary[i][0];
       }
       
       public String getVietNam(int i){
           return dictionary[i][1];
       }
       public int getSoTu(){
           return n;
       }
       
       public int findWordEnglish(String str){
           for(int i = 0; i < n; i++){
               if(dictionary[i][0].equalsIgnoreCase(str)){
                   return i;
               }
           }
           return -1;
       }
       public int findWordVietNam(String str){
           for(int i = 0; i < n; i++){
               if(dictionary[i][1].equalsIgnoreCase(str)){
                   return i;
               }
           }
           return -1;
       }
       
       public void addWord(String eng, String viet){
           dictionary[n][0]= eng;
           dictionary[n][1]= viet;
       }
       
       public boolean modidyWordEnglish(String old , String newEng, String newVi){
           int vitri = findWordEnglish(old);
           if(vitri != -1){
               dictionary[vitri][0] = newEng;
               dictionary[vitri][1] = newVi;
               return true;
           }
           return false;
       }
       
       public void ghiFileAnh(String data){
           try {
            File file = new File("anh.txt");
            if (!file.exists()) {
               file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getName(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("\r\n"+data);
            bw.close();
            System.out.println("Done");
                } catch (Exception e) {
                    e.printStackTrace();
                }
       }
       public void ghiFileViet(String data){
           try {
            File file = new File("viet.txt");
            if (!file.exists()) {
               file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getName(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("\r\n"+data);
            bw.close();
            System.out.println("Done");
                } catch (Exception e) {
                    e.printStackTrace();
                }
       }
       
       public void xoaTu(int i){
           
               dictionary[i][0] = dictionary[n][0];
               dictionary[i][1] = dictionary[n][1];   
               n--;
           
           try {
            File file = new File("anh.txt");
            if (!file.exists()) {
               file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getName());
            BufferedWriter bw = new BufferedWriter(fw);
            for(int k = 1; k < n+1; k++){
                bw.write(dictionary[k][0]+":"+dictionary[k][1]+"\n");
            }
            
            bw.close();
            System.out.println("Done");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                   
           
       }
       public void setSoLuong(int k){
           n = k;
       }
}

