package poly.crol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class listTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<HashMap<String,String>> nList = new ArrayList<HashMap<String,String>>();
		
		HashMap<String,String> hash = new HashMap<String,String>();
		String str = "제목";
		String repeat = "1";
		
		hash.put(str,repeat);
		nList.add(hash);
		
		HashMap<String,String> hash2 = new HashMap<String,String>();
		String str2 = "제목2";
		String repeat2 = "2";
		
		hash.put(str2,repeat2);
		nList.add(hash2);
		
		
		for(int i = 0; i<nList.size();i++) {
		System.out.println("nList:"+nList.get(2));
		}
	}

}
