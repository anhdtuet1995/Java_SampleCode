/**
 *
 * Author: Dao Tuan Anh
 * UET - 11/2015
 *
 */


import java.util.*;
import java.io.*;

public class DictionarySimple{
	public static void removeLineFromFile(String file, String lineToRemove) {
 
		try {	
			File inFile = new File(file);
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
			BufferedReader br = new BufferedReader(new FileReader(file));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
			String line = null;
			
			while ((line = br.readLine()) != null) {
				
				if (!line.trim().equals(lineToRemove)) {
					pw.println(line);
					pw.flush();
				}
			}
			pw.close();
			br.close();
			if (!inFile.delete()) {
				System.out.println("Could not delete file");
				return;
			} 
			if (!tempFile.renameTo(inFile))
				System.out.println("Could not rename file");
			  
		}
		catch (FileNotFoundException ex) {
			 ex.printStackTrace();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		VA v = new VA();
		AV a = new AV();
		Scanner input = new Scanner(System.in);
		int c;

		while(true){
			System.out.println("1. Tra tu Anh-Viet");
			System.out.println("2. Tra tu Viet-Anh");
			System.out.println("3. Sua tu Anh-Viet");
			System.out.println("4. Sua tu Viet-Anh");
			System.out.println("5. Them tu Anh-Viet");
			System.out.println("6. Them tu Viet-Anh");
			System.out.println("7. Thoat");
			System.out.println("Nhap lua chon cua ban: ");
			c = input.nextInt();
			switch(c){
				case 1:{
					Scanner inA = new Scanner(System.in);
					System.out.println("Nhap tu muon tim: ");
					String anh = inA.nextLine();
					System.out.println("Nghia cua tu:");
					System.out.println(a.searchWord(anh) + "\n\n");
					break;
				}
				case 2:{
					Scanner inV = new Scanner(System.in);
					System.out.println("Nhap tu muon tim: ");
					String viet = inV.nextLine();
					System.out.println("Nghia cua tu:");
					System.out.println(v.searchWord(viet) + "\n\n");
					break;	
				}
				case 3:{
					Scanner inA = new Scanner(System.in);
					System.out.println("Nhap tu tieng anh muon sua: ");
					String anh = inA.nextLine();
					if(a.hasWord(anh)){
						Scanner ch = new Scanner(System.in);
						System.out.println("1. Sua tu: ");
						System.out.println("2. Sua nghia: ");
						System.out.println("3. Thoat");
						System.out.println("Lua chon:");
						int ch2 = ch.nextInt();
						switch(ch2){
							case 1:{
								System.out.println("Sua tu thanh: ");
								String anhNew = inA.nextLine();
								a.editWord(anh, anhNew);
								break;
							} 
							case 2:{
								System.out.println("Sua nghia thanh: ");
								String mean = inA.nextLine();
								a.editMean(anh, mean);
								break;
							}
							default: break;
						}
						System.out.println("Sua tu thanh cong!!\n\n");	
					}
					else{
						System.out.println("Tu nay khong co trong tu dien\n\n");
					}
					
					break;	
				}
				case 4:{
					Scanner inV = new Scanner(System.in);
					System.out.println("Nhap tu tieng viet muon sua: ");
					String vie = inV.nextLine();
					if(v.hasWord(vie)){
						Scanner ch3 = new Scanner(System.in);
						System.out.println("1. Sua tu: ");
						System.out.println("2. Sua nghia: ");
						System.out.println("3. Thoat");
						System.out.println("Lua chon:");
						int chV = ch3.nextInt();
						switch(chV){
							case 1:{
								System.out.println("Sua tu thanh: ");
								String vieNew = inV.nextLine();
								v.editWord(vie, vieNew);
								break;
							} 
							case 2:{
								System.out.println("Sua nghia thanh: ");
								String meanE = inV.nextLine();
								v.editMean(vie, meanE);
								break;
							}
							default: break;
						}
						System.out.println("Sua tu thanh cong!!\n\n");
					}
					else{
						System.out.println("Tu nay khong co trong tu dien\n\n");
					}
					break;	
				}
				case 5:{
					Scanner addE = new Scanner(System.in);
					System.out.println("Nhap tu tieng anh: ");
					String eA = addE.nextLine();
					System.out.println("Nhap nghia tieng viet: ");
					String eV = addE.nextLine();
					if(!a.hasWord(eA)){
						a.addWord(eA, eV);
						System.out.println("Them tu thanh cong!!");	
					}
					else{
						System.out.println("Them tu that bai!!");
					}
					System.out.println("\n\n");
					break;
				}
				case 6:{
					Scanner addV = new Scanner(System.in);
					System.out.println("Nhap tu tieng viet: ");
					String vV = addV.nextLine();
					System.out.println("Nhap nghia tieng anh: ");
					String vE = addV.nextLine();
					if(!a.hasWord(vV)){
						a.addWord(vV, vE);
						System.out.println("Them tu thanh cong!!");	
					}
					else{
						System.out.println("Them tu that bai!!");
					}
					System.out.println("\n\n");
					break;
				}
				default: break;
			}
			if(c > 6) break;
		}
	}
}

class AV{
	public TreeMap<String, String> av = new TreeMap<String, String>();

	public AV(){
		try{		
			File file = new File("Anh-Viet.txt");
			if(!file.exists() && file.isDirectory()) { 
    			file.createNewFile();
			}
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while((line = br.readLine()) != null){
				String []tmp = line.split(":");
				av.put(tmp[0], tmp[1]);
			}
			br.close();
		}
		catch(FileNotFoundException ex){}
		catch(IOException e){}
	}

	public boolean hasWord(String a){
		return av.containsKey(a);
	}

	public void addWord(String a, String v){
		if(!av.containsKey(a)){
			av.put(a, v);
			try{
				File file = new File("Anh-Viet.txt");
				BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
				if(file.length() == 0){
					bw.write(a + ":" + v);
					bw.close();	
				}
				else{
					bw.newLine();
					bw.write(a + ":" + v);
					bw.close();
				}
				
			}
			catch(FileNotFoundException ex){}
			catch(IOException e){}
		}
	}

	public void editWord(String a, String aNew){
		try{
			if(av.containsKey(a)){
				String tmp = av.get(a);
				DictionarySimple.removeLineFromFile("Anh-Viet.txt", a+":"+tmp);
				BufferedWriter bw = new BufferedWriter(new FileWriter("Anh-Viet.txt", true));
				bw.write(aNew + ":" + tmp);
				av.remove(a);
				av.put(aNew, tmp);
				bw.close();
			}
		}
		catch(FileNotFoundException ex){}
		catch(IOException e){}
	}

	public void editMean(String a, String vNew){
		try{
			if(av.containsKey(a)){
				String tmp = av.get(a);
				DictionarySimple.removeLineFromFile("Anh-Viet.txt", a+":"+tmp);
				BufferedWriter bw = new BufferedWriter(new FileWriter("Anh-Viet.txt", true));
				bw.write(a + ":" + vNew);
				av.remove(a);
				av.put(a, vNew);
				bw.close();
			}
		}
		catch(FileNotFoundException ex){}
		catch(IOException e){}	
	}

	public String searchWord(String a){
		if(av.containsKey(a)){
			return av.get(a);
		}
		return "Khong co tu nay";
	}

}

class VA{
	
	public TreeMap<String, String> va = new TreeMap<String, String>();

	public VA(){
		try{
			File file = new File("Viet-Anh.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while((line = br.readLine()) != null){
				String []tmp = line.split(":");
				va.put(tmp[0], tmp[1]);
			}
		}
		catch(FileNotFoundException ex){}
		catch(IOException e){}
	}

	public boolean hasWord(String v){
		return va.containsKey(v);
	}

	public void addWord(String v, String a){
		if(!va.containsKey(v)){
			va.put(v, a);
			try{
				File file = new File("Viet-Anh.txt");
				BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
				if(file.length() == 0){
					bw.write(v + ":" + a);
					bw.close();	
				}
				else{
					bw.newLine();
					bw.write(v + ":" + a);
					bw.close();
				}
				
			}
			catch(FileNotFoundException ex){}
			catch(IOException e){}
		}
	}

	public void editWord(String v, String vNew){
		try{
			if(va.containsKey(v)){
				String tmp = va.get(v);
				DictionarySimple.removeLineFromFile("Viet-Anh.txt", v+":"+tmp);
				BufferedWriter bw = new BufferedWriter(new FileWriter("Viet-Anh.txt", true));
				bw.write(vNew + ":" + tmp);
				va.remove(v);
				va.put(vNew, tmp);
				bw.close();
			}
		}
		catch(FileNotFoundException ex){}
		catch(IOException e){}
	}

	public void editMean(String v, String aNew){
		try{
			if(va.containsKey(v)){
				String tmp = va.get(v);
				DictionarySimple.removeLineFromFile("Viet-Anh.txt", v+":"+tmp);
				BufferedWriter bw = new BufferedWriter(new FileWriter("Viet-Anh.txt", true));
				bw.write(v + ":" + aNew);
				va.remove(v);
				va.put(v, aNew);
				bw.close();
			}
		}
		catch(FileNotFoundException ex){}
		catch(IOException e){}	
	}

	public String searchWord(String v){
		if(va.containsKey(v)){
			return va.get(v);
		}
		return "Khong co tu nay";
	}	

}