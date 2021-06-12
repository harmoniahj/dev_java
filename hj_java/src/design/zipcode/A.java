package design.zipcode;

import java.util.ArrayList;

public class A {

	public static void main(String[] args) {
		ArrayList a1 = new ArrayList();
		a1.add("수박");
		a1.add("딸기");
		a1.add("사과");
		/* 제네렉이 없는 경우 이믜로 타입 확인X, Object인거 까지만 확인이 가능
		for(String str:a1) { 
			System.out.println(str);
		} */
		
		for(Object str:a1) {
			System.out.println(str);
		}

		String my = (String)a1.get(0); // 0번째 배열에 있는것을 가져옴
		Object obj = a1.get(2);
		System.out.println(my);
		System.out.println(obj);
		System.out.println("============================");
		
		ArrayList<String> a2 = new ArrayList<String>();
		a2.add("수박");
		a2.add("딸기");
		a2.add("사과");
	// 내 안에 어떤 타입이 있는지를 명시하는 것
		for(String str:a2) {
			System.out.println(str);
		}
		String my2 = (String)a2.get(0);
		Object obj2 = a2.get(2);
		System.out.println(my);
		System.out.println(obj);
		
		ArrayList<ZipCodeVO> a3 = new ArrayList<>();
		ZipCodeVO zVO = new ZipCodeVO();
		zVO.setZipcode(21356);
		zVO.setAptname("서울시 마포구 공덕동 246번지");
		a3.add(zVO);
		zVO.setZipcode(21356);
		zVO.setAptname("서울시 영등포구 영등포동");
		a3.add(zVO);
		zVO.setZipcode(21356);
		zVO.setAptname("서울시 구로구 고척동");
		a3.add(zVO);
		for(ZipCodeVO rzVO:a3) {
			System.out.println(rzVO);
		}
		for(ZipCodeVO rzVO:a3) {
			//String addr = rzVO
			System.out.println(rzVO);
		}
	}
}