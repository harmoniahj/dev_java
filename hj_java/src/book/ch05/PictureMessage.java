package book.ch05;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class PictureMessage extends JDialog {
	String imgPath = "D:\\workspace_java\\hj_java\\src\\img\\";
	JPanel jp_emotion = new JPanel();
	
	GridLayout gl_emotion = new GridLayout(1, 5, 2, 2);
	JButton pic = new JButton();
	JButton pic2 = new JButton();
	JButton pic3 = new JButton();
	JButton pic4 = new JButton();
	JButton pic5 = new JButton();
	String imgFile[] = {"lion11.png", "lion22.png", "lion33.png", "lion44.png", "lion55.png"};
	JButton imgButton[] = {pic, pic2, pic3, pic4, pic5};
	ImageIcon img[] = new ImageIcon[imgButton.length];
	
	public PictureMessage() {
	}
	
	public void initDisplay() {
	//	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		for(int i=0; i<imgFile.length; i++) {
			
			img[i] = new ImageIcon(imgPath + imgFile);
			imgButton[i].setDisabledIcon(img[i]);
		}

		jp_emotion.setLayout(gl_emotion);
		for(int j=0; j< imgButton.length; j++) {
			imgButton[j].setIcon(new ImageIcon(imgPath + imgFile[j]));
			jp_emotion.add(imgButton[j]);
		}
		
		this.add("Center", jp_emotion);
		this.setSize(500,200);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		PictureMessage msg = new PictureMessage();
		msg.initDisplay();
	}

}