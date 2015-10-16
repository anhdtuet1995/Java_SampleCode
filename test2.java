import java.util.*;
import java.io.*;

public class test2{

	public static void main(String[] args) {
		int soMatHang = 3;
		boolean flag = true;
		//boolean flagWhile = true;
		Scanner input = new Scanner(System.in);
		Invoice invoice = new Invoice();

		//Item item = new Item[soMatHang];
		ArrayList<Item> item = new ArrayList<>();
		ArrayList<Item> sanPhamDaChon = new ArrayList<>();
		item.add(new Item("PC", 123));
		item.add(new Item("Laptop", 12));
		item.add(new Item("Iphone", 2));


		/*
		* Danh sach cac san pham con trong kho
		*/
		int tt= 0;
		System.out.println("Danh sach cac mat hang con lai trong kho: ");
		System.out.println("TT\t\tTen\t\t\tDon Gia");
		for (Item i: item){
			System.out.println(tt + "\t\t" + i.getTen() + "\t\t\t" + i.getPrice());
			tt++;
		}

		System.out.println("Nhap don hang: ");
		while(true){
			int soLuongSanPham;
			System.out.println("Mat hang: ");
			String matHangCanMua = input.next();
			//if(matHangCanMua.equals("")) flag = false; 
			if(matHangCanMua.equals("done")) break;
			Item temp  = new Item();
			for(Item i: item){
				if(!matHangCanMua.equals(i.getTen())){
					flag = false;
				}
				else{
					temp = i;
					flag = true;
				}
				if(flag){
					break;	
				} 
			}
			if(!temp.getTen().equals("")){
				System.out.println("Co san pham! So luong: ");
				soLuongSanPham = input.nextInt();
				invoice.addInvoiceLineItem(new InvoiceLineItem(temp, soLuongSanPham));
			}
			else{
				System.out.println("Khong co san pham nay");
			}
		}
		int tt2 = 0;
		System.out.println("In hoa don thanh toan:");
		System.out.println("TT\t\tTen\t\tSo Luong\tThanh Tien");
		for (InvoiceLineItem i : invoice.line){
			System.out.println(tt2 + "\t\t" + i.toString() + "\t\t" + i.getSubtotal());
			tt2++;
		}
		System.out.println("\t\t\t\tTong tien: \t" + invoice.getTotal());
		//do something	
	}

}

class Item{
	
	private String ten;
	private double donGia;
	private String moTa;

	public Item(){
		this.ten = "";
		this.donGia = 0.0;
		this.moTa = "";
	}

	public Item(String ten, double donGia){
		this.ten = ten;
		this.donGia = donGia;
	}

	public Item(String ten, double donGia, String moTa){
		
		this.ten = ten;
		this.donGia = donGia;
		this.moTa = moTa;

	}

	public String getTen(){
		return this.ten;
	}

	public double getPrice(){
		return this.donGia;
	}

	public void setPrice(double donGia){
		this.donGia = donGia;
	}

	//do something
}

class InvoiceLineItem{
	
	private Item matHang;
	private int soLuong;

	public InvoiceLineItem(Item matHang, int soLuong){
		this.matHang = matHang;
		this.soLuong = soLuong;
	}

	public void setItem(Item matHang){
		this.matHang = matHang;
	}

	public void setQuantity(int q){
		this.soLuong = q;
	}

	public double getSubtotal(){
		return ( matHang.getPrice() * soLuong);
	}
	
	public String toString(){
		return matHang.getTen() + "\t\t" + soLuong;
	}
	//do something

}

class Invoice{
	
	public ArrayList<InvoiceLineItem> line = new ArrayList<>();

	public void addInvoiceLineItem( InvoiceLineItem i){
		this.line.add(i);
	}

	public double getTotal(){
		if(!line.isEmpty()){
			double tong = 0;
			for(InvoiceLineItem l: line){
				tong += l.getSubtotal(); 
			}
			return tong;
		} 
		else return -1;
	}
	//do something
}