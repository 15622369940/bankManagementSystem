import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.text.*;

//�ļ�ѡ�����Ͳ˵�
public class MyFrame9 extends JFrame implements ActionListener{
	private MyPanel oneJPanel;
	
	private JMenuBar mainJMenuBar;
	
	private JMenu fileJMenu;
	private JMenu editJMenu;
	private JMenu formatJMenu;
	
	private JMenu newFileJMenuItem;
	private JMenuItem saveFileJMenuItem;
	private JMenuItem openFileJMenuItem;
	
	private JMenuItem newOneFileJMenuItem;
	private JMenuItem newProgramJMenuItem;
	
	private JTextArea fileJTextArea;
	
	private JFileChooser fileJFileChooser;
	
	public MyFrame9(){
		init();
		Calendar c = Calendar.getInstance();
		System.out.println (c);
		Date d = c.getTime();
		java.text.SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		System.out.println (f.format(d));
		
		this.setVisible(true);
	}
	
	private void init(){
		this.setSize(400,400);
		this.setLocationRelativeTo(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = this.getContentPane();
		
		fileJTextArea = new JTextArea();
		fileJTextArea.setLineWrap(true);
		c.add(fileJTextArea);
		
		oneJPanel = new MyPanel();
		oneJPanel.setLayout(null);
		//c.add(oneJPanel);
		
		mainJMenuBar = new JMenuBar();
		this.setJMenuBar(mainJMenuBar);
		
		fileJMenu = new JMenu("�ļ�");
		mainJMenuBar.add(fileJMenu);
		
		newFileJMenuItem = new JMenu("�½�(A)");
	//	newFileJMenuItem.addActionListener(this);
		fileJMenu.add(newFileJMenuItem);
		
		newOneFileJMenuItem = new JMenuItem("�ļ�");
		newFileJMenuItem.add(newOneFileJMenuItem);
		
		newProgramJMenuItem = new JMenuItem("����");
		newFileJMenuItem.add(newProgramJMenuItem);
		
		saveFileJMenuItem = new JMenuItem("����(A)",KeyEvent.VK_A);
		fileJMenu.add(saveFileJMenuItem);
		saveFileJMenuItem.addActionListener(this);
		fileJMenu.addSeparator();
		
		openFileJMenuItem = new JMenuItem("��(A)",KeyEvent.VK_A);
		fileJMenu.add(openFileJMenuItem);
		
		editJMenu = new JMenu("�༭");
		mainJMenuBar.add(editJMenu);
		
		formatJMenu = new JMenu("��ʽ");
		mainJMenuBar.add(formatJMenu);
		
		fileJFileChooser = new JFileChooser("f:");
	}
	
	public void actionPerformed(ActionEvent e){
		int result = fileJFileChooser.showSaveDialog(this);
		if(result == JFileChooser.APPROVE_OPTION){
		//	fileJTextArea.show(false);
			
			
			File file = fileJFileChooser.getSelectedFile();
			
			try{
				//����� 
				String str = fileJTextArea.getText();
				String[] strs = str.split("\r\n");
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
//				for (int i = 0; i<strs.length; i++){
//					bw.write(str);
//					bw.flush();
//				}
				bw.write(str);
				
				bw.close();
				fw.close();
				
				//�򿪵�
//				FileReader fr = new FileReader(file);
//				BufferedReader br = new BufferedReader(fr);
//				StringBuffer sb = new StringBuffer();
//				String str = "";
//				while((str = br.readLine())!=null){
//					fileJTextArea.append(str+"\r\n");
//				}
//				//fileJTextArea.setText(sb.toString())	;
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
}