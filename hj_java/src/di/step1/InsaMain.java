package di.step1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InsaMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("di\\step1\\insaBean.xml");
		
		InsaList insalist = (InsaList)context.getBean("insa");
		InsaMap insaMap = (InsaMap)context.getBean("insaMap");
		
		System.out.println("mapBean ==>" + insaMap);
		System.out.println(insalist);
		System.out.println(insalist.insaBean);
		
		for(String s:insalist.insaBean) {
			System.out.println(s);
		}
	}
}