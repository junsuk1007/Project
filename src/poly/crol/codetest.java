package poly.crol;

public class codetest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str = "[이영미 人터뷰] 감독, 코치들로 이룬 여농 ‘어벤져스’, BNK 레전드들";

		System.out.println("original str :" + str);

		str = str.replaceAll("[^\\uAC00-\\uD7A3xfe0-9a-zA-Z\\\\s]", " ");

		System.out.println("1st str :" + str);

		str = str.replaceAll("\\s{2,}", " ");

		System.out.println("2nd str :" + str);

		str = str.trim();

		System.out.println("3nd str :" + str);

		String[] strArr = str.split(" ");

		System.out.println("strArr.length :" + strArr.length);

		//마지막 글자 없애는 함수
		System.out.println("네번째 배열 조사삭제:" + strArr[3].substring(0, strArr[3].length() - 1));

		for (int i = 0; i < strArr.length; i++) {
			System.out.println("strArr[" + i + "]번째 :" + strArr[i]);

			String checkstr = strArr[i].substring(strArr[i].length() - 1);

			if ((checkstr.equals("은")) || (checkstr.equals("는")) || (checkstr.equals("이")) || (checkstr.equals("가"))
					||(checkstr.equals("을")) ||(checkstr.equals("를"))||(checkstr.equals("와"))||(checkstr.equals("랑"))
					||(checkstr.equals("로"))) {

				strArr[i] = strArr[i].substring(0, strArr[i].length() - 1);

				System.out.println("바뀐 strArr[" + i + "]번째 :" + strArr[i]);
			}
			

		}
		
		for(int k = 0; k <strArr.length; k++) {
			System.out.println("최종 strArr[" + k + "]번째 :" + strArr[k]);			
		}

	}

}
