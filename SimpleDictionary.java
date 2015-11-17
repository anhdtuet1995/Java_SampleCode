import java.util.*;
import java.io.*;

public class SimpleDictionary
{
	public TreeMap <String, String> a = new TreeMap<String, String>();
	
	public SimpleDictionary() throws IOException{
		File fileDir = new File("dictData.txt");
		BufferedReader br = new BufferedReader(new FileReader(fileDir));
		String s = new String();
		while((s = br.readLine()) != null){
			String[] tmp = s.split(":");
			a.put(tmp[0], tmp[1]);
		}
		br.close();
	}
	
	//Print list all words in dictionary
	public void listWords(){
		System.out.println("Tieng Anh-----Tieng Viet");
		for(Map.Entry<String, String> entry: a.entrySet()){
				System.out.println(entry.getKey() + "------" + entry.getValue());
		}
	}
	
	//Add a word into dictionary
	public void addWord(String eng, String vie) throws IOException{
		if(!a.containsKey(eng)){
			String tmp = eng + ":" + vie;
			a.put(eng, vie);
			File fileDir = new File("dictData.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileDir, true));
			bw.newLine();
			bw.write(tmp);
			bw.close();
		}
		else{
			
			if(a.get(eng).contains(",")){
				boolean flag = true;
				String[] mean = a.get(eng).split(",");
				for(int i=0; i<mean.length; i++){
					if(vie.equals(mean[i])){
						flag = false;
						break;
					}
				}
				if(flag == true){
					String s = eng + ":" + a.get(eng);
					SimpleDictionary.removeLineFromFile("dictData.txt", s);
					a.put(eng, a.get(eng) + "," + vie);
					File fileDir = new File("dictData.txt");
					BufferedWriter bw = new BufferedWriter(new FileWriter(fileDir, true));
					bw.write(eng + ":" +a.get(eng));
					bw.close();
				}
			}
			
			else{
				if(!a.get(eng).equals(vie)){
					String s = eng + ":" + a.get(eng);
					SimpleDictionary.removeLineFromFile("dictData.txt", s);
					a.put(eng, a.get(eng) + "," + vie);
					File fileDir = new File("dictData.txt");
					BufferedWriter bw = new BufferedWriter(new FileWriter(fileDir, true));
					bw.write(eng + ":" +a.get(eng));
					bw.close();
				}
			}
		}
	}
	
	//search English and translate to Vietnamese
	public String searchEng(String eng){
		if(a.containsKey(eng)){
			return a.get(eng);
		}
		return "meo co dau em";
	}
	
	//search Vietnamese and translate to English
	public String searchVie(String vie){
		String s = new String();
		for(Map.Entry<String, String> entry: a.entrySet()){
			if(entry.getValue().contains(",")){
				String[] tmp = entry.getValue().split(",");
				for(int i = 0; i < tmp.length; i++){
					if(tmp[i].equals(vie)){
						s += entry.getKey() + ",";
						break;
					}
				}
			}
			else{
				if(entry.getValue().equals(vie)){
					s += entry.getKey() + ",";
				}
			}
		}
		if(s.contains(",")){
			return s.substring(0,s.length()-1);
		}
		return s;
	}
	
	//edit mean of eng
	public void editWordEng(String eng){
		try{
			String vieNew = new String();
			if(searchEng(eng) != null){
				String vieOld = a.get(eng);
				SimpleDictionary.removeLineFromFile("dictData.txt", eng + ":" + vieOld);
				
				System.out.println("Nhap lai cac nghia tieng viet cua tu " + eng + ": ");
				System.out.println("(Nhap vao chu n de dung viec nhap tu)");
				Scanner input = new Scanner(System.in);
				String tmp = new String();
				while(true){
					tmp = input.nextLine();
					if(tmp.equals("n")){
						break;
					} 
					else{
						vieNew += tmp + ",";
					}
				}
			}
			if(vieNew.contains(",")){
				vieNew = vieNew.substring(0, vieNew.length()-1);
				File fileDir = new File("dictData.txt");
				BufferedWriter bw = new BufferedWriter(new FileWriter(fileDir, true));
				bw.write(eng + ":" +vieNew);
				bw.close();
			}
		}
		catch (FileNotFoundException ex) {
			 ex.printStackTrace();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	//edit a word eng -> engNew
	public void editWordEng(String eng, String engNew){
		try{
			if(searchEng(eng) != null){
				String vieOld = a.get(eng);
				SimpleDictionary.removeLineFromFile("dictData.txt", eng + ":" + vieOld);
				a.remove(eng);
				a.put(engNew, vieOld);
				File fileDir = new File("dictData.txt");
				BufferedWriter bw = new BufferedWriter(new FileWriter(fileDir, true));
				bw.write(engNew + ":" +vieOld);
				bw.close();
			}
		}
		catch (FileNotFoundException ex) {
			 ex.printStackTrace();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	//edit a word vie (add engNew)
	public void editWordVie(String vie, String engNew){
		try{
			if(searchVie(vie).length() >= 1){
				addWord(engNew, vie);
			}
			else{
				System.out.println("lam cut gi co ma doi sua");
			}
		}
		catch(FileNotFoundException ex){
			ex.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		
	}
	
	//remove line from file
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

	public static void main(String[] args) throws IOException{
		Scanner input = new Scanner(System.in);
		SimpleDictionary dict = new SimpleDictionary();
		while(true){
			System.out.println("Menu");
			System.out.println("1. Tra tu Anh - Viet");
			System.out.println("2. Tra tu Viet - Anh");
			System.out.println("3. Them tu");
			System.out.println("4. Danh sach cac tu");
			System.out.println("5. Sua tu Anh - Viet");
			System.out.println("6. Sua tu Viet - Anh");
			System.out.println("7. Thoat");
			System.out.println("Lua chon chua ban: ");
			int choise = input.nextInt();
			switch(choise){
				case 1:{
					Scanner engIp = new Scanner(System.in);
					System.out.println("Nhap tu tieng Anh can tim: ");
					String eng = engIp.nextLine();
					System.out.println(dict.searchEng(eng));
					break;
				}
				case 2:{
					Scanner vieIp = new Scanner(System.in);
					System.out.println("Nhap tu tieng Viet can tim: ");
					String vie = vieIp.nextLine();
					System.out.println(dict.searchVie(vie));
					break;
				}
				case 3:{
					Scanner vieEngIp = new Scanner(System.in);
					System.out.println("Nhap tu tieng Anh muon them: ");
					String eng = vieEngIp.nextLine();
					System.out.println("Nhap nghia cua tu: ");
					String vie = vieEngIp.nextLine();
					dict.addWord(eng, vie);
					break;
				}	
				case 4:{
					System.out.println("Danh sach cac tu: ");
					dict.listWords();
					break;
				}
				case 5:{
					Scanner c = new Scanner(System.in);
					System.out.println("1. Sua tu TA");
					System.out.println("2. Sua nghia");
					int choise2 = c.nextInt();
					switch(choise2){
						case 1:{
							Scanner cc = new Scanner(System.in);
							System.out.println("Nhap tu tieng anh muon sua: ");
							String eng = cc.nextLine();
							System.out.println("Sua thanh: ");
							String engNew = cc.nextLine();
							dict.editWordEng(eng, engNew);
							break;
						}
						case 2:{
							Scanner cc = new Scanner(System.in);
							System.out.println("Nhap tu tieng anh muon sua: ");
							String eng = cc.nextLine();
							dict.editWordEng(eng);
							break;
						}
						default: break;
					}
					break;
				}
				case 6:{
					Scanner vieIp = new Scanner(System.in);
					System.out.println("Nhap tu tieng Viet muon sua: ");
					String vie = vieIp.nextLine();
					System.out.println("Nghia tieng anh: ");
					String eng = vieIp.nextLine();
					dict.editWordVie(vie, eng);
					break;
				}
				default:{
					break;
				}
			}
			if(choise > 6) break;
		}
		
		
	}
}