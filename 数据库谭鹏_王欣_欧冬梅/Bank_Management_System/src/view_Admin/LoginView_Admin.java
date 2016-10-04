package view_Admin;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import util.MD5Util;
import view_ATM.LoginView_User;



//�����ǹ���Ա��½����
public class LoginView_Admin extends JFrame{
	
	private JLabel account_numLabel = new JLabel("�˺�");
	private JTextField account_numField = new JTextField();
	
	private JLabel passwordLabel = new JLabel("����");
	private JTextField passwordField = new JPasswordField();
	
	private JButton button1 = new JButton("��½");
    private JButton button2 = new JButton("ȡ��"); 
    
	public LoginView_Admin(){
		
		this.setLayout(null);  //���Բ���
		this.setTitle("��ӭ�������й���ϵͳ");
		this.setResizable(false);
		this.setSize(360, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		
		account_numLabel.setBounds(40, 25, 40, 20);
		add(account_numLabel);
		
		account_numField.setBounds(120, 25, 140, 20);
		add(account_numField);
		
		passwordLabel.setBounds(40, 60, 40, 20);
		add(passwordLabel);
		
		passwordField.setBounds(120, 60, 140, 20);
		add(passwordField);
		
		button1.setBounds(160,100,60, 25);
		add(button1);
		button2.setBounds(240, 100, 60, 25);
		add(button2);
		
		account_numField.setText("6217002940103996053");
		passwordField.setText("tanpeng");
		//��½����
		button1.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
				String account_num = account_numField.getText();
				String password  = passwordField.getText();
				password = MD5Util.getMD5(MD5Util.getMD5(password));
				
				//�����п�
				if("".equals(account_num)||account_num==null){
					JOptionPane.showMessageDialog(null, "�����˺Ų���Ϊ��");
					account_numField.setText("");
					passwordField.setText("");
					return;
				}
				if("".equals(password)||password==null){
					JOptionPane.showMessageDialog(null, "�������벻��Ϊ��");
					account_numField.setText("");
					passwordField.setText("");
					return ;
				}
				
				//JDBC  
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

					String sql = "select userAccount,userPassword from userinfo where userAccount = ? and userPassword = ? and ifadmin = 1";
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setObject(1, account_num);
					ps.setObject(2, password);
					
					ResultSet rs = ps.executeQuery();  //ִ��Sql���
					
					if(rs.next()){
						String account = account_num;
						LoginView_Admin.this.dispose();   //�رո�ҳ��
						MainView_Admin mainView = new MainView_Admin(account);
						
					}else{
						JOptionPane.showMessageDialog(null, "�˺Ż��������");
						account_numField.setText("");
						passwordField.setText("");
					}
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
		});
		

		button2.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
				LoginView_Admin.this.dispose();
				System.exit(0);	
				
				
			}
			
		});
		//
		
		//��Ӽ����¼����رմ���
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				int n = JOptionPane.showConfirmDialog(null, "���Ҫ�˳���","��ʾ", JOptionPane.YES_NO_OPTION);
			
			if(n==0){
				System.exit(0);				
			}else{
				//�����������
				return;
			}
			}
			
		});
		this.setVisible(true);
		
	}

}
