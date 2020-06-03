package poly.crol;

import poly.util.EncryptUtil;

public class Sha_test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String str = "admin";
		
		System.out.println("1st str :" + str);
		str = EncryptUtil.encHashSHA256(str);
		System.out.println("2nd str :" + str);
	}

}
