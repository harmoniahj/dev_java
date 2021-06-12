package di.step1;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InsaList {
	List<String> insaBean = null;

	public void setInsaBean(List<String> insaBean) {
		this.insaBean = insaBean;
	}
}