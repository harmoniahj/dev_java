package book.ch12;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListExam1 {
	public void methodA(List<String> list) { // 메소드 오버로딩
		System.out.println("methodA(List) 호출");
	}
	
	public void methodB(List list) {
		
	}
	
	public void methodA(Collection<String> list) { // 메소드 오버로딩
		System.out.println("memthodB(Collection) 호출");
	}
	
	public void methodA(ArrayList<String> list) { // 메소드 오버로딩
		System.out.println("memthodB(ArrayList) 호출");
	}
	
	public static void main(String[] args) {
		ListExam1 exam = new ListExam1();
		ArrayList<String> alist = new ArrayList<>();
		System.out.println("size는 얼마인가??" + alist.size());
		alist.add("딸기");
		System.out.println("size는 얼마인가??" + alist.size());
		exam.methodA(alist);
		exam.methodA(alist);
		
		ArrayList<String> alist2 = new ArrayList<>();
		exam.methodA(alist2);
		exam.methodA(alist2);
		exam.methodA(alist2);
	}
}