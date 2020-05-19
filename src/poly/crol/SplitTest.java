package poly.crol;


public class SplitTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str = "[2020FA] 유일했던 보상 FA 이적, KCC는 4명 보호... 전자랜드 선택은?";
		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
		String replaceStr = str.replaceAll(match, "");
		String[] splitStr = replaceStr.split("\\s");
		
		System.out.println("str : " + str);
		System.out.println("replaceStr : " + replaceStr);
				
		
		for(int i=0; i<splitStr.length;i++) {
			System.out.println("splitStr["+i+"] = " + splitStr[i]);
			
		}
	}
	

	

}
