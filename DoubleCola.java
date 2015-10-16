import java.util.*;

public class DoubleCola{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		Queue<String> list = new LinkedList<String>();
		list.add("Sheldon");
		list.add("Leonard");
		list.add("Penny");
		list.add("Rajesh");
		list.add("Howard");

		int count = 1;
		while(count < n){
			String tmp = list.element();
			list.remove();
			list.add(tmp);
			list.add(tmp);
			count++;
		}
		System.out.println("Nguoi uong lon co ca thu " + n + ": " + list.element());
	}
}