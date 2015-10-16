import java.util.*;

public class SubSet{

    public void listSubsets(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> sub, ArrayList<Integer> a) {
        int len = a.size();
        if (len == 0) result.add(sub);
        else {
            result.add(sub);
            for (int i = 0; i < len; i++) {
                ArrayList<Integer> tmp1 = new ArrayList<Integer>(sub);
                tmp1.add(a.get(i));
                ArrayList<Integer> tmp2 = new ArrayList<Integer>(a);
                tmp2.remove(i);
                listSubsets(result, tmp1, tmp2);
            }
        }
    }
    public static ArrayList<ArrayList<Integer>> copyArrayList(ArrayList<ArrayList<Integer>> source){
        ArrayList<ArrayList<Integer>> destination = new ArrayList<ArrayList<Integer>>();
        for (ArrayList<Integer> sou : source){
            // ArrayList<Integer> temp = new ArrayList<Integer>(6);
             System.out.println( " " + sou.size());
            // Collections.copy(temp, sou);
            // destination.add(temp);
        }
        return destination;
    }

    public int countAdd(ArrayList<ArrayList<Integer>> sub, int m){
        ArrayList<ArrayList<Integer>> temp = SubSet.copyArrayList(sub);
        
        int count = 0;
        int result = 0;
        for(int i = 0; i < temp.size(); i++){
            Collections.sort(temp.get(i));
        }
        for(int i = 0; i < temp.size() - 1; i++){
            for(int j = i + 1; j < temp.size(); j++){
                if(temp.get(i).equals(temp.get(j))){
                    temp.remove(j);
                }
            }
        }

        for (ArrayList<Integer> su : temp){
            for (Integer i : su){
                result += i;
            }
            if(result == m){
                count++; 
            }
            result = 0;
        }
        return count;
    }
    
    public int countMultiply(ArrayList<ArrayList<Integer>> sub, int m){
        ArrayList<ArrayList<Integer>> temp = SubSet.copyArrayList(sub);
        int count = 0;
        int result = 1;

        for(int i = 0; i < temp.size() -1; i++){
            Collections.sort(temp.get(i));
        }
        for(int i = 0; i < temp.size() - 1; i++){
            for(int j = i + 1; j < temp.size(); j++){
                if(temp.get(i).equals(temp.get(j))){
                    temp.remove(j);
                }
            }
        }

        for (ArrayList<Integer> su : temp){
            for (Integer i : su){
                result *= i;
            }
            if(result == m){
                count++; 
            }
            result = 1;
        }
        return count;
    }

    public int countSubtract(ArrayList<ArrayList<Integer>> sub, int m){
        ArrayList<ArrayList<Integer>> temp = SubSet.copyArrayList(sub);
        //temp.clear();
        int count = 0;

        for(ArrayList<Integer> i: temp){
            if ( i.size() > 1 ){
                int result = i.get(0);
                for (int j = 1; j < i.size(); j++ ){
                    result -= i.get(j);
                }
                if(result == m) count++;
            }
        }
        return count;
    }

    public int countDivide(ArrayList<ArrayList<Integer>> sub, int m){
        ArrayList<ArrayList<Integer>> temp = SubSet.copyArrayList(sub);
        int count = 0;

        for(ArrayList<Integer> i: temp){
            if ( i.size() > 1 ){
                double result = (double)i.get(0);
                for (int j = 1; j < i.size(); j++ ){
                    result = (double)result/i.get(j);
                }
                if(result == (double)m) count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        SubSet a = new SubSet();
        int []s = {1,6,2,5,3};

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> subTest = new ArrayList<Integer>();
        ArrayList<Integer> sub2 = new ArrayList<Integer>();
        subTest.add(1);
        subTest.add(6);
        subTest.add(2);
        subTest.add(5);
        subTest.add(3);
        
        a.listSubsets(result, sub2, subTest);
        
        System.out.println("Add: " + a.countAdd(result, 15));
        System.out.println("Multiply: " + a.countMultiply(result, 15));
        
        //a.listSubsets(result, sub2, subTest);
        
        System.out.println("Subtract: " + a.countSubtract(result, 5));  
        System.out.println("Divide: " + a.countDivide(result, 15));  
    }
}