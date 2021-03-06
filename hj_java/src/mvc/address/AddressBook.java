package mvc.address;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;

import java.util.*;
import java.util.List;

public class AddressBook extends JFrame {
	Logger logger = LogManager.getLogger(AddressBook.class);
	
	private JMenuBar menuBar;
    private JMenu menuMenu;
    private JMenu menuAbout;
	private JMenuItem menuItemConnect;
    private JMenuItem menuItemInsert;
    private JMenuItem menuItemUpdate;
    private JMenuItem menuItemDelete;
    private JMenuItem menuItemAll;
    private JMenuItem menuItemDetail;
    private JMenuItem menuItemAbout;
    private JSeparator menuSeparator1;
    private JSeparator menuSeparator2;
    private JMenuItem menuItemExit;
    private JToolBar toolbar;
    private JButton btnInsert;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnAll;
    private JButton btnDetail;
    private JScrollPane jScrollPane1;
    private JTable table;
	private MyTableModel myTableModel;
	private JPanel panelTimer;
	private JLabel labelTimer;

	private JOptionPane optionDlg;
	private ModifyDialog mDialog;
	private Font font;
	private String path;

 // DB작업을 중개해줄 Controller 클래스
	private AddressCtrl ctrl;
	public static AddressBook abook = null;

 // 메인 메소드는 AddressBook의 인스턴스를 생성하고 보여주는 일만 합니다.
    public static void main(String args[]) {
    	System.setProperty(XmlConfigurationFactory.CONFIGURATION_FILE_PROPERTY, "log4j.xml");	
        abook = new AddressBook();
        abook.setVisible(true);
    }

 // 생성자는 컴포넌트들을 초기화합니다.
    public AddressBook() {
        initComponents();
    }

 // 초기화 작업은 컴포넌트들의 값을 셋팅하고 배치합니다.
    private void initComponents() {
     // DefaultTableModel을 상속받은 MyTableModel 클래스가 테이블의 데이타 담당
		myTableModel= new MyTableModel();

	// 메뉴바, 메뉴, 메뉴 아이템을 정의
        menuBar = new JMenuBar();
        menuMenu = new JMenu();
		menuAbout= new JMenu();
		menuItemConnect= new JMenuItem();
        menuItemInsert = new JMenuItem();
        menuItemUpdate = new JMenuItem();
        menuItemDelete = new JMenuItem();
        menuItemAll= new JMenuItem(); // 전체조회
        menuItemDetail= new JMenuItem(); // 상세조회 1건
		menuItemAbout = new JMenuItem();
        menuSeparator1 = new JSeparator();
        menuSeparator2 = new JSeparator();
        menuItemExit = new JMenuItem();
        menuMenu.setText("메뉴");
        menuMenu.setFont(font);

   // 툴바와 이미지 버튼을 정의합니다.
        toolbar = new JToolBar();
        btnInsert = new JButton();
        btnInsert.setToolTipText("입력");
        btnUpdate = new JButton();
        btnUpdate.setToolTipText("수정");
        btnDelete = new JButton();
        btnDelete.setToolTipText("삭제");
        btnAll = new JButton();
        btnAll.setToolTipText("전체조회");
        btnDetail = new JButton();
        btnDetail.setToolTipText("상세조회");
        jScrollPane1 = new JScrollPane();
        table = new JTable(myTableModel);

		labelTimer = new JLabel("현재 시간");
		labelTimer.setFont(font);
		panelTimer = new JPanel();
		font = new Font("굴림", 0, 12);
		path = "src//mvc//address//";

   // About 화면을 출력할 대화상자 정의
		optionDlg = new JOptionPane();
	 // 입력, 수정, 조회에 사용할 화면정의
        mDialog = new ModifyDialog(this);
        mDialog.setVisible(false);

   // DB연결 메뉴아이템
		menuItemConnect.setFont(font);
		menuItemConnect.setText("DB 연결");
		menuItemConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				logger.info("DB 연결 메뉴");
                connectActionPerformed(evt);
			}
		});
		
   // 전체조회 메뉴아이템
		menuItemAll.setFont(font);
		menuItemAll.setText("전체조회");
		menuItemAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	logger.info("전체 조회 메뉴");
                refreshData();
            }
        });

   // 상세조회 메뉴아이템
        menuItemDetail.setFont(font);
        menuItemDetail.setText("상세조회");
        menuItemDetail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	logger.info("조회 메뉴");
                detailActionPerformed();
            }
        });

   // 입력 메뉴아이템
	    menuItemInsert.setFont(font);
        menuItemInsert.setText("입력");
        menuItemInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	logger.info("입력 메뉴");
                addActionPerformed(evt); // 원봉 사용할 수 있음
            }
        });

   // 수정 메뉴아이템
        menuItemUpdate.setFont(font);
        menuItemUpdate.setText("수정");
        menuItemUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	logger.info("수정 메뉴");
            	AddressVO vo = new AddressVO();
            	updateActionPerformed(vo);
            }
        });

   // 삭제 메뉴아이템
        menuItemDelete.setFont(font);
        menuItemDelete.setText("삭제");
        menuItemDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	logger.info("삭제 메뉴");
            	deleteActionPerformed();
            }
        });

   // 종료 메뉴아이템
        menuItemExit.setFont(font);
        menuItemExit.setText("종료");
        menuItemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	logger.info("종료 메뉴");
            	exitActionPerformed(evt);
            }
        });

   // 메뉴 아이템을 메뉴에 붙입니다.
		menuMenu.add(menuItemConnect);
        menuMenu.add(menuSeparator1);
        menuMenu.add(menuItemAll);
		menuMenu.add(menuItemDetail);
		menuMenu.add(menuItemInsert);
		menuMenu.add(menuItemUpdate);
		menuMenu.add(menuItemDelete);
		menuMenu.add(menuSeparator2);
        menuMenu.add(menuItemExit);

   // 첫번째 메뉴를 메뉴바에 붙입니다.
        menuBar.add(menuMenu);

   // About 메뉴
		menuAbout.setFont(font);
		menuAbout.setText("About");

   // About 메뉴아이템
		menuItemAbout.setFont(font);
		menuItemAbout.setText("About");
		menuItemAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				logger.info("About 메뉴");
				aboutActionPerformed(evt);
			}
		});
		menuAbout.add(menuItemAbout);

   // 두번째 메뉴를 메뉴바에 붙입니다.
		menuBar.add(menuAbout);
   // 완성된 메뉴바를 프레임과 연결합니다.
		setJMenuBar(menuBar);

   // 프레임 관련 설정을 합니다.
		setTitle("주소록 데모 프로그램");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFont(font);

   // 윈도우 리스너 설정
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
            	logger.info("윈도우 종료");
            	System.exit(0);
            }
        });

   // 상세조회 아이콘
		btnDetail.setIcon(new ImageIcon(path + "detail.gif"));
        btnDetail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	logger.info("조회 아이콘");
            	detailActionPerformed();
            }
        });
        toolbar.add(btnDetail);
		toolbar.add(new JToolBar.Separator());

   // 입력 아이콘
        btnInsert.setIcon(new ImageIcon(path + "new.gif"));
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	logger.info("입력 아이콘");
            	addActionPerformed(evt);
            }
        });
		toolbar.add(btnInsert);

   // 수정 아이콘
        btnUpdate.setIcon(new ImageIcon(path + "update.gif"));
		btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	logger.info("수정 아이콘");
            	AddressVO vo = new AddressVO();
            	updateActionPerformed(vo);
            }
        });
        toolbar.add(btnUpdate);
        
   // 삭제 아이콘
        btnDelete.setIcon(new ImageIcon(path + "delete.gif"));
		btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	logger.info("삭제 아이콘");
            	deleteActionPerformed();
            }
        });
        toolbar.add(btnDelete);

   // 툴바를 컨테이너에 붙입니다.
        getContentPane().add(toolbar, java.awt.BorderLayout.NORTH);

   // 데이터 리스트가 조회될 테이블을 설정합니다.
        table.setFont(font);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() >= 2) {
					logger.info("데이타 더블클릭");
					detailActionPerformed();
				}
			}
		});

   // 테이블을 스크롤 페널에 연결하여 붙입니다.
        jScrollPane1.setViewportView(table);
        getContentPane().add(jScrollPane1, BorderLayout.CENTER);

   // 서버로부터 받아온 시간을 출력할 레이블을 붙입니다.
		panelTimer.add(labelTimer);
		getContentPane().add(panelTimer, BorderLayout.SOUTH);

        pack(); // setSize대신에 사용
        
   // 데이터가 표시될 테이블의 칼럼을 설정합니다.
		myTableModel.setColumnCount(0);

		myTableModel.addColumn("아이디");
		table.getColumnModel().getColumn(0).setWidth(300);
		myTableModel.addColumn("이름");
		table.getColumnModel().getColumn(0).setWidth(300);
		myTableModel.addColumn("주소");
		table.getColumnModel().getColumn(0).setWidth(800);
		myTableModel.addColumn("전화번호");
		table.getColumnModel().getColumn(0).setWidth(500);

   // 모든 GUI 컴포넌트의 설정이 완료되면 전체 데이터를 조회합니다.
		try {
			refreshData(); // 열리자마자 화면 조회해서 뿌림
		} catch (Exception e) {
			optionDlg.showMessageDialog(this, "DB 연결에 실패했습니다.\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
		}
    }

   // DB연결 메뉴 선택시 작업을 정의합니다.
	private void connectActionPerformed(ActionEvent evt) {

		try {
			refreshData();
		} catch (Exception e) {
			optionDlg.showMessageDialog(this, "DB 연결에 실패했습니다.\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

 // 상세조회 메뉴나 조회 아이콘 선택
	private void detailActionPerformed() {
		
	}

 // 입력 메뉴나 입력 아이콘 선택
	private void addActionPerformed(ActionEvent evt) {

	}

 // 수정 메뉴나 수정 아이콘 선택
	public void updateActionPerformed(AddressVO pvo) {

	}

 // 삭제 메뉴나 삭제 아이콘 선택시 작업을 정의합니다.
	private void deleteActionPerformed() {
		int index[] = table.getSelectedRows();
		AddressVO pVO = new AddressVO();
		for(int i=0;i<myTableModel.getRowCount();i++) {
			if(table.isRowSelected(i)) {
				Integer id = Integer.parseInt((String)myTableModel.getValueAt(i, 0));
				pVO.setId(id);
				pVO.setCommand("delete");
				ctrl = new AddressCtrl();
				try {
					ctrl.send(pVO);					
				} catch (Exception e) {
					logger.info("Exception:"+e.toString());
				}

			}
		}
	}

 // 종료 메뉴 선택시 작업을 정의합니다.
	private void exitActionPerformed(ActionEvent evt) {
		System.exit(0);
	}

// About 메뉴 선택시 작업을 정의
	private void aboutActionPerformed(ActionEvent evt) {
		optionDlg.setFont(font);
		optionDlg.showMessageDialog(this, "주소록 데모 프로그램 1.0", "About", JOptionPane.INFORMATION_MESSAGE);
	}

   // 전체 데이터를 다시 조회
	public void refreshData() {
		AddressVO paVO = new AddressVO();
		paVO.setCommand("selectall");
		AddressCtrl abCtrl = new AddressCtrl();
		List<AddressVO> list = abCtrl.sendAll();
	}
}

// 데이터를 보여줄 테이블의 실제 데이터를 관리하는 클래스입니다.
class MyTableModel extends DefaultTableModel {
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
}