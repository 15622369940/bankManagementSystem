package view_ATM;

import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Inquiry_end extends JDialog{
	
	private JTextArea tishiArea = new JTextArea();
	
	public Inquiry_end(String account ) {
		
		this.setLayout(null);  //���Բ���
		
		tishiArea.setBounds(30, 30, 300, 180);
		add(tishiArea);
		
		tishiArea.setText("1Ӣ�� = 9.7027�����"+"\n"+"1��Ԫ = 6.2097�����\n"+"1�۱� = 0.801�����");
		
		
		this.setTitle("���ʲ�ѯ");
		this.setResizable(false);   //�����϶������
		this.setSize(360, 260);
		this.setLocationRelativeTo(null);   //����
		this.setVisible(true);   //������ӻ�
	}
	public static void main(String[] args) {
		Inquiry_end inquiry_end = new Inquiry_end("350964661");
	}

}
