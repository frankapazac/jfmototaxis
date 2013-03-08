package Test;

import java.util.ArrayList;
import java.util.List;

public class testArrays {
	public static void main(String[] args) {
		List<Integer> proc=new ArrayList<Integer>();
		proc.add(1);proc.add(2);proc.add(3);
		List<Integer> list=new ArrayList<Integer>();
		list.add(2);list.add(4);
		
		for(Integer y:list){
			proc.remove(y);
		}
		for(Integer x:proc){
			System.out.println("Eliminar "+x);
		}
		
		for(Integer x:proc){
			list.remove(x);
		}
		for(Integer y:list){
			System.out.println("Insertar "+y);
		}
	}
}
