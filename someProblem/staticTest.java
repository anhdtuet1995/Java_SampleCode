import java.util.*;

public class staticTest{
	public static void main(String[] args) {
		System.out.println(staticTest2.a);
		staticTest2.printNumber2();
	}
}

class staticTest2{
	static final int a;
	public static void printNumber(){
		System.out.println(10);
	}
	public static void printNumber2(){
		a = 5;
		System.out.println(a);
	}
}