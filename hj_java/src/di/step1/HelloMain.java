package di.step1;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
/*
 * spring-core.jar > IOC(Inversion of Conversion : 제어역행)를 제공하는 역할을 함
 * 최근에 사용되는 모든 컨테이너들이 공통으로 사용하고 있는 개념
 * 
 * 기존방식 > 자바기반으로 APP 개발할 때 자바객체를 생성하고 서로의 의존관계를 연결시키는 작업에 대한 제어권은  보통 개발되는 APP에 있음
 * 문제제기 > 컴포넌트간의 결바도가 높아 확장 및 재사용이 어려운 문제접이 발생함
 * 해결 방법 > IoC사용 : 제어권이 Container에게 넘어가 객체의 생명주기를Container가 전담하게 됨
 * 해결내용 > 컴포넌트 간의 결합도가 낮아져 컴포넌트 재사용 밑 확장이 쉽고, 의존관계 변경이 쉬움
 */
public class HelloMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("di\\step1\\helloBean.xml");
		ApplicationContext context2 = new ClassPathXmlApplicationContext("di\\step1\\sonataBean.xml");
		
		Sonata himCar = (Sonata)context2.getBean("himCar");
		Sonata herCar = (Sonata)context2.getBean("herCar");
		
		System.out.println(himCar);
		System.out.println(herCar);
		
		Resource resource = new FileSystemResource("D:\\workspace_java\\hj_java\\src\\di\\step1\\helloBean.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		HelloBean helloBean2 = (HelloBean)factory.getBean("helloBean222");
		HelloBean helloBean = (HelloBean)context.getBean("helloBean222");
		System.out.println(helloBean.getGreeting("Hi~"));
		System.out.println(helloBean2.getGreeting("Hi~"));
	}
}