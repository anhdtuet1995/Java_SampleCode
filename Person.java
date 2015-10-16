import java.util.*;
import java.io.*;

public class Person{
	String lastName, firstName;
	
	public Person(String l, String f){
		this.lastName = l;
		this.firstName = f;
	}

	public static void main(String[] args) throws IOException{
        ArrayList<Person> p = new ArrayList<>();
        File file = new File("a.txt");
	    String absolutePath = file.getAbsolutePath();
		BufferedReader br= new BufferedReader(new FileReader(absolutePath));
		
		String name;
		while ((name = br.readLine()) != null){
			String[] a = name.split(", ", 2);
			p.add(new Person(a[0], a[1]));
		}
		
		br.close();
		Collections.sort(p, new Comparator<Person>(){
			@Override
			public int compare(Person p1, Person p2){
				int value = p1.firstName.compareTo(p2.firstName);
				if(value != 0){
					return value;
				}
				value = p1.lastName.compareTo(p2.lastName);
				return value;
			}
		});

		for(Person i: p){
			System.out.println(i.lastName + " " + i.firstName);
		}

		PrintWriter pw = new PrintWriter("b.txt", "UTF-8");
		for(Person i: p){
			pw.println(i.lastName + ", " + i.firstName);
		}
		pw.close();
	}
}
