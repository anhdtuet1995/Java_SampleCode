import java.util.*;

public class Camera{


	public static void main(String[] args) {
		Queue<String> cam1 = new LinkedList<>();
		cam1.add("a11");
		cam1.add("b22");
		cam1.add("c33");
		cam1.add("d44");
		cam1.add("e55");
		Queue<String> cam2 = new LinkedList<>();
		cam2.add("c33");
		cam2.add("b22");
		cam2.add("a11");
		cam2.add("e55");
		cam2.add("d44");
		ArrayList<String> fail = new ArrayList<>();

		while(!cam2.isEmpty()){
			for(int i = 0; i < fail.size(); i++){
				if (fail.get(i) == cam1.element()){
					cam1.remove();
				}
			}

			if(cam1.element() == cam2.element()){
				cam1.remove();
				cam2.remove();
			}
			else{
				fail.add(cam2.element());
				cam2.remove();
			}

		}

		for (String f: fail){
			System.out.println(f);
		}
		
	}
}