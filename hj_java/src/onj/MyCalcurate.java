package onj;

/*********************************************************
 * sValue1 > 처음에 입력한 숫자값
 * sValue2 > 연산자 다음으로 입력한 숫자값
 * sOp > 연산자
 *********************************************************/

public class MyCalcurate {
	public static String calcurate(String sValue1, String sValue2, String sOp) {
		double v1 = Double.parseDouble(sValue1);
		double v2 = Double.parseDouble(sValue2);

		System.out.println("sValue1 : "+sValue1+" : sValue2 : "+sValue2 + "==> "+sOp);
		if(sOp.equals("+")){
			return ""+(v1+v2);
		}
		else{
			return "error";
		}

	}
}