package design.zipcode;

import java.util.List; // 자료구조
import java.util.Vector;

public class ZipCodeList {

	public static void main(String[] args) {
	// 꺽쇠는 제네릭, 생성부에는 타입X 다이아몬드 연산자만 붙임(뒤에 생략가능)
		Vector<ZipCodeVO> v = new Vector<>(); // 다형성 누릴 수X
		List<ZipCodeVO> v2 = new Vector<>(); // 다형성 누림
		ZipCodeVO zcVO = null;
		ZipCodeVO zcVOs[] = null;
		
		int i = 0;
		while(i<3) {
//			zcVO.setAddress("서울시"); > NllPointExeception
			zcVO = new ZipCodeVO();
			zcVO.setAddress("서울시");
			System.out.println("zcVO 주소번지 바뀜 ==> " + zcVO.getAddress());
			v.add(zcVO);
			zcVO.setAddress("인천직할시"); // vector에 반영X
			i++;
		}
		
		for(int x=0; x<v.size(); x++) {
			ZipCodeVO  zVO = v.get(x); // 17에서 만들어진 VO와는 다른것
			System.out.println(zVO); // 다른 주소번지가 출력
		}
		
		zcVOs = new ZipCodeVO[v.size()]; // 객체배열 3개 만들어짐
		System.out.println("배열의 크기는 " + zcVOs.length);
		v.copyInto(zcVOs); // 벡터에 담긴것을 배열에 담아줌
		
		for(int y=0; y<zcVOs.length; y++) {
			System.out.println(zcVOs[y] + ", " + zcVOs[y].getAddress()); // 객체배열의 주소번지 3개 출력
		}
	}
}