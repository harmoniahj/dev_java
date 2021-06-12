package book.ch14;

public class UserExceptionTest {
	public void test(String[] a) throws UserException {
		System.out.println("test");
		if(a.length < 1) // 아무것도 X
			throw new UserException("아무것도 X");
		else
			throw new UserException("최종 예선", 7000);
	}

	public static void main(String[] args) {
		UserExceptionTest test = new UserExceptionTest();
		try {
			test.test(args);
		} catch (UserException ue) {
		//	System.out.println(ue.printStackTrace()); > 사용X
		//	ue.printStackTrace();
			System.out.println("[[[[ UserException ]]]]");
			System.exit(0); // JVM과의 연결 끊어줌
		} catch(Exception e) {
			e.toString();
			e.getMessage();
			e.printStackTrace();
		}  finally {
			System.out.println("finally");
		}
	}

}