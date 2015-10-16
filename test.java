import java.util.*;
import java.io.*;


public class test{
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int sosv;

		char []dapAn = {'B', 'C', 'D', 'B', 'A', 'D', 'C', 'D', 'A', 'B'};
		char []traLoi = new char[10];
		Random ran = new Random();
		
		int diem = 0;
		
		System.out.println("Dap an la: ");
		for (char c: dapAn){
			System.out.print(c + " ");
		}
		
		System.out.println("\nNhap so sinh vien: ");
		sosv = input.nextInt();
		int bangDiem[] = new int[sosv];

		System.out.println("\nBan da chon: ");
		for (int i=0; i<sosv; i++){
			System.out.println("\nCau tra loi sinh vien thu " + (i+1) +": ");
			for (int j=0; j<10; j++){
				char c = (char)(ran.nextInt(4) + 'a');
				c = Character.toUpperCase(c);
				System.out.print(c + " "); 
				if (c == dapAn[j]) diem++;
			}	
			bangDiem[i] = diem;
			diem = 0;
		}
		System.out.println("\nBang diem");
		for (int i=0; i<sosv; i++){
			System.out.println("Diem cua thi sinh thu " + (i+1) + " : " + bangDiem[i] + "/10");	
		}
		
	}
	
}
