package ui;

import javax.swing.JFrame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookManager extends JFrame {
	Logger logger = LogManager.getLogger(BookManager.class);
	
	JFrame jf = new JFrame(); // new Window()
	static JFrame bm = new BookManager();
	static BookManager bm2 = new BookManager(); // new JFrame()
	static BookManager bm3 = null;
	
	public BookManager() {
//		bm.initDisplay(); > 교집합만 처리되기에...
	//	initDisplay();

	//	this.setSize(500, 300); // this > BookManager
		this.setSize(500, 300); // super > JFrame
		this.setVisible(true); // 
	}
	
	public void initDisplay() {
		logger.info("initDisplay()");
	}

	public static void main(String[] args) {
		bm3 = new BookManager();
		bm3.initDisplay();
	}

}