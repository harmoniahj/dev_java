package design.zipcode;

import java.util.ArrayList;

public class UniTest {

	public static void main(String[] args) {
		ZipCodeDao zcd = new ZipCodeDao();
		ArrayList<ZipCodeVO> zipcodelist = null; //
		zipcodelist = zcd.getZipCodeList("가산동");
		zipcodelist = zcd.getZipCodeList("당산동");
		System.out.println(zipcodelist);
	} 

}
