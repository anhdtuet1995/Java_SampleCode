import java.util.*;

public class FindWord{
	public static int n = 7, m = 9;
	public static char [][] a = {
									{'e', 't', 'a', 'k', 'a', 'q', 's', 'l', 's'}, 
									{'q', 'p', 'l', 'l', 'u', 'r', 'c', 'c', 'y'},
									{'x', 'f', 'a', 'c', 'd', 'x', 'a', 'u', 't'},
									{'b', 'c', 'c', 'l', 'a', 'l', 'l', 'd', 'h'},
									{'s', 'n', 's', 'c', 'a', 'l', 'a', 'g', 'd'},
									{'m', 'v', 't', 'i', 'd', 'c', 'a', 'j', 'n'},
									{'n', 'u', 'd', 'h', 'a', 'k', 's', 'x', 't'}
								};

	public static ArrayList<Location> timViTri(char [][] a, String b){
		ArrayList<Location> tmp = new ArrayList<>();
		char s = b.charAt(0);

		for(int i = 0; i < n; i++){
			for (int j = 0; j < m; j++){
				if(a[i][j] == s){
					tmp.add(new Location(i, j));
				}
			}
		}
		return tmp;
	}

	public static int hangDoc(char [][] a, int x, int y, String s){
		int len = s.length();
		int count = 0;
		int c1 = 1;
		int c2 = 1;

		if ( x >= len - 1 && x < FindWord.n){
			int tmp = x;
			for (int i = 1; i < len; i++){
				tmp--;
				if(a[tmp][y] == s.charAt(i)) {
					c1++;
				}
			}	
		}
		
		if ( x < len - 1 && x >= 0 ){
			int tmp = x;
			for (int i = 1; i < len; i++){
				tmp++;
				if(a[tmp][y] == s.charAt(i)){
					c2++;
				}
			}
		}
		
		if(c1 == s.length()) count++;
		if(c2 == s.length()) count++;
		return count;
	}

	public static int tongHangDoc(char[][] a, ArrayList<Location> b, String s){
		int sum = 0;
		for ( Location i : b){
			sum += FindWord.hangDoc(a, i.x, i.y, s);
		}
		return sum;
	}

	public static int hangNgang(char [][] a, int x, int y, String s){
		int len = s.length();
		int count = 0;
		int c1 = 1;
		int c2 = 1;
		int tmp;

		if ( y >= len - 1 && y < FindWord.m){
			tmp = y;
			for (int i = 1; i < len; i++){
				tmp--;
				if(a[x][tmp] == s.charAt(i)) {
					c1++;
				}
			}	
		}
		
		if ( y < len - 1 && y >= 0 ){
			tmp = y;
			for (int i = 1; i < len; i++){
				tmp++;
				if(a[x][tmp] == s.charAt(i)){
					c2++;
				}
			}
		}
		
		if(c1 == s.length()) count++;
		if(c2 == s.length()) count++;
		return count;
	}

	public static int tongHangNgang(char[][] a, ArrayList<Location> b, String s){
		int sum = 0;
		for ( Location i : b){
			sum += FindWord.hangNgang(a, i.x, i.y, s);
		}
		return sum;
	}

	public static int cheo(char [][] a, int x, int y, String s){
		int len = s.length();
		int count = 0, c1 = 1, c2 = 1, c3 = 1, c4 = 1;
		int tmp1, tmp2;
		//tren phai duoi trai chieu thuan
		if ( x >= 0 && x < FindWord.n - len && y >= len - 1 && y < FindWord.m ){
			tmp1 = x; 
			tmp2 = y;
			
			for (int i = 1; i < len; i++){
				tmp1++;
				tmp2--;
				if(a[tmp1][tmp2] == s.charAt(i)) c1++;
			}
		}

		//tren trai duoi phai chieu thuan
		if ( x >= 0 && x < FindWord.n - len && y < FindWord.m - len && y >= 0 ){
			tmp1 = x; 
			tmp2 = y;
			
			for (int i = 1; i < len; i++){
				tmp1++;
				tmp2++;
				if(a[tmp1][tmp2] == s.charAt(i)) c1++;
			}
		}

		//tren phai duoi trai chieu nghich

		if ( x < FindWord.n && x >= len-1 && y < FindWord.m - len && y >= 0 ){
			tmp1 = x; 
			tmp2 = y;
			
			for (int i = 1; i < len; i++){
				tmp1--;
				tmp2++;
				if(a[tmp1][tmp2] == s.charAt(i)) c3++;
			}
		}

		//tren trai duoi phai chieu nghich
		if ( x < FindWord.n && x >= len-1 && y >= len - 1 && y < FindWord.m  ){
			tmp1 = x; 
			tmp2 = y;
			
			for (int i = 1; i < len; i++){
				tmp1--;
				tmp2--;
				if(a[tmp1][tmp2] == s.charAt(i)) c4++;
			}
		}


		if(c1 == s.length()) count++;
		if(c2 == s.length()) count++;
		if(c3 == s.length()) count++;
		if(c4 == s.length()) count++;
		return count;
	}

	public static int tongCheo(char[][] a, ArrayList<Location> b, String s){
		int sum = 0;
		for ( Location i : b){
			sum += FindWord.cheo(a, i.x, i.y, s);
		}
		return sum;
	}

	public static void main(String[] args) {
		String s = "scala";
		
		ArrayList<Location> ex = new ArrayList<>();
		ex = timViTri(a, s);
		System.out.println(tongHangNgang(a, ex, s));
		System.out.println(tongHangDoc(a, ex, s));
		System.out.println(tongCheo(a, ex, s));
	}

}

class Location{
	public int x;
	public int y;
	public Location(int x, int y){
		this.x = x;
		this.y = y;
	}
}