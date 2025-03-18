package collections;

import java.util.ArrayList;
import java.util.List;

public class ListSetExe {
	public static void main(String[] args) {
		Integer[] iAry = { 10, 20, 30 };
		for(int i=0;i<iAry.length;i++) {
			System.out.println(iAry[i]);
		}
		
		List<Integer> ilist = new ArrayList<Integer>();
		ilist.add(10);
		ilist.add(20);
		ilist.add(30);
		
		for(int i=0;i<ilist.size();i++) {
			
		}
	}
}
