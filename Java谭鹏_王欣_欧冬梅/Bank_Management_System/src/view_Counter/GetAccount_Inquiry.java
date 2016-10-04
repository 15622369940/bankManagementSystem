package view_Counter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import util.MD5Util;
import view_ATM.Transfer_accounts_Main;
import view_Admin.GetAccount_main;
import view_Admin.MainView_Admin;
import view_Counter.Transfer_accounts_Main_counter;


public class GetAccount_Inquiry extends JDialog{
	
	private JLabel account_numLabel = new JLabel("�˺�");
	private JTextField account_numField = new JTextField();

	
	
	private JLabel passwordLabel = new JLabel("����");
	private JTextField passwordField = new JPasswordField();

	private JButton button1 = new JButton("ȷ��");
	private JButton button2 = new JButton("����");

	final DecimalFormat df = new DecimalFormat("0.00");
	public  GetAccount_Inquiry( final String account_admin) {
		// �ڹ��췽��������в���
		final int n = 0;
		this.setLayout(null); // ���Բ���

		account_numLabel.setBounds(40, 25, 40, 20);
		add(account_numLabel);

		account_numField.setBounds(120, 25, 140, 20);
		add(account_numField);

		account_numField.setText("6217002940103996053");
		
		passwordLabel.setBounds(40, 60, 40, 20);
		add(passwordLabel);

		passwordField.setBounds(120, 60, 140, 20);
		add(passwordField);

		button1.setBounds(140, 100, 70, 25);
		add(button1);

		button2.setBounds(230, 100, 70, 25);
		add(button2);

		button1.addActionListener(new ActionListener() { // ��Ӽ����¼�

					public void actionPerformed(ActionEvent w) {
						// TODO Auto-generated method stub
						// ���ı����л�ȡ����ֵ
						String account_num = account_numField.getText();
						String password = passwordField.getText();

						if ("".equals(account_num) || account_num == null) {
							JOptionPane.showMessageDialog(null, "�����˺Ų���Ϊ�գ�\n\t"
									+ "�����²�����");
							account_numField.setText("");
							passwordField.setText("");
							return;
						}
						if ("".equals(password) || password == null) {
							JOptionPane.showMessageDialog(null, "�������벻��Ϊ�գ�\n\t"
									+ "�����²�����");
							account_numField.setText("");
							passwordField.setText("");
							return;
						}

						try {
							password = MD5Util.getMD5(MD5Util.getMD5(password));
							// �����ݿ�
							Class.forName("com.mysql.jdbc.Driver");
							Connection connection = DriverManager.getConnection(
									"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

							String sql = "select userAccount,userPassword from userinfo where userAccount = ? and userPassword = ? ";
							PreparedStatement ps = connection
									.prepareStatement(sql);
							ps.setObject(1, account_num);
							ps.setObject(2, password);

							ResultSet rs = ps.executeQuery();
							if (rs.next()) {

								GetAccount_Inquiry.this.dispose(); // �رոý��棬������뵽������
								String account = rs.getString(1);
								
								Inquiry_counter inquiry_counter = new Inquiry_counter(account_admin,account);
								
								
							} else {
								JOptionPane.showMessageDialog(null, "��½ʧ�ܣ�");
								account_numField.setText("");
								passwordField.setText("");
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
		button2.addActionListener(new ActionListener() {

			

			public void actionPerformed(ActionEvent e) {
				
				GetAccount_Inquiry.this.dispose();
				
				MainView_Admin mainView_Admin = new MainView_Admin(account_admin);

			}

		});
		// ��Ӽ����¼� ��������¼� �رմ���
		this.addWindowListener(new WindowAdapter() { // WindowAdapter
														// �ǳ����࣬����ʵ�����������ǵ��������ڲ���
					// ��д override
					public void windowClosing(WindowEvent e) {
						int n = JOptionPane.showConfirmDialog(null, "���Ҫ�˳���",
								"��ʾ", JOptionPane.YES_NO_OPTION);

						if (n == 0) {
							System.exit(0);
						} else {
							// �����������
							return;
						}
					}
				});
		this.setTitle("��ӭ�������й���ϵͳ");
		this.setResizable(false); // �����϶������
		this.setSize(360, 200);
		this.setLocationRelativeTo(null); // ����
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // �رգ���ʲô������
		this.setVisible(true); // ������ӻ�
	}


}
