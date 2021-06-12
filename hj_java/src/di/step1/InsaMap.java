package di.step1;

import java.util.Map;

public class InsaMap {
	Map<String, String> mapBean = null;
	
 // DBConnection 풀도 자바 표준에서 제공하는 것은 아니니까 생성자 객체 주입법으로 처리해야 할 것(xml에서 처리하는 것)
 // myBatis의 경우 java가 아닌 자바코드는 jar형태로 주입되므로 constructor주입법으로 처리됨
 // setter객체 주입법 > java
	public void setMapBean(Map<String, String> mapBean) {
		this.mapBean = mapBean;
	}
}