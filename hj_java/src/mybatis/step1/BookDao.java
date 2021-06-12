package mybatis.step1;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.core.io.Resource;

public class BookDao {
	String resource = "mybatis/step1/MapperConfig.xml";
 // 이것을 통해 xml문서의 정보를 읽어 커넥션을 얻어내는데 사용
	SqlSessionFactory sqlMapper = null;
	
	public List<Map<String, Object>> getBookList() {
		List<Map<String, Object>> booklist = null;
		SqlSession session = null; 
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			session = sqlMapper.openSession();
			String currentTime = session.selectOne("currentTime");
			System.out.println("currentTime => " + currentTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return booklist;
	}

	public static void main(String[] args) {
		BookDao bDao = new BookDao();
		List<Map<String, Object>> booklist = null;
		booklist = bDao.getBookList();
		System.out.println(booklist);
	}
}