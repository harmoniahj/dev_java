package design.zipcode;

import java.util.ArrayList;
import java.util.Scanner;

public class ZipCodeSearchConsole {
	String zdo = null;
	
	//생성자
	public ZipCodeSearchConsole() {
		
	}
	
	public void printZipCode(ArrayList<ZipCodeVO> zipCode) {
		for(ZipCodeVO zcVO:zipCode) {
			System.out.println(zcVO.getAddress() + ", " + zcVO.getZipcode());
		}
	}
	
	public String userInput() {
		String userDong = null;
		Scanner scan = new Scanner(System.in);
		userDong = scan.nextLine();
		return userDong;
	}

	//메인메소드
	public static void main(String[] args) {
		System.out.println("동을 입력하세요");
		String userDong = null;
		ZipCodeSearchConsole zcs = new ZipCodeSearchConsole();
		userDong = zcs.userInput();
		ZipCodeDao zcDAO = new ZipCodeDao();
		ArrayList<ZipCodeVO> al = zcDAO.getZipCodeList(userDong);
		zcs.printZipCode(al);
	}
	
	public void refreshData(String zdo, String dong) {
		System.out.println("zdo : " + zdo + "dong : " + dong);
		try {
			
		} catch (Exception e) {
			System.out.println("Exception : " + e.toString());
		}
	}
}