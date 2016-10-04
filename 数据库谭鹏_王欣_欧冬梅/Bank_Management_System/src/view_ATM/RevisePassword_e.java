package view_ATM;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import util.MD5Util;

import method.WindowListener;


public class RevisePassword_e extends JDialog{
	
	private JLabel tishiLabel = new JLabel("���ٴ����������������ȷ�ϼ�");
	private JTextField newpasswordField = new JPasswordField();
	private JButton yesButton = new JButton("ȷ��");
	private JLabel jLabel = new JLabel("");
	private JButton exitButton = new JButton("�˳�");
	private JButton clearButton = new JButton("���");
	
	public RevisePassword_e(final String  account,final String password1) {
		this.setTitle("�޸�����");
		this.setSize(500, 350);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		// this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLayout(null);

		tishiLabel.setBounds(50, 30, 400, 40);
		add(tishiLabel);
		tishiLabel.setFont(new Font("΢���ź�", Font.PLAIN, 26));

		jLabel.setBounds(60, 180, 140, 59);
		add(jLabel);
		jLabel.setFont(new Font("", Font.PLAIN, 21));

		newpasswordField.setBounds(150, 100, 200, 50);
		add(newpasswordField);
		newpasswordField.setFont(new Font("΢���ź�", Font.PLAIN, 20));

		exitButton.setBounds(40, 240, 95, 40);
		add(exitButton);
		yesButton.setBounds(310, 180, 95, 40);
		add(yesButton);
		clearButton.setBounds(310, 240, 95, 40);
		add(clearButton);

		//���һ��ȷ�ϣ�д�����ݿ�
		//int account,String password1
		yesButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String e_account = account ;
				
				String newPassword1 = password1;
				String newPassword = newpasswordField.getText();
				newPassword = MD5Util.getMD5(MD5Util.getMD5(newPassword));
				newPassword1 = MD5Util.getMD5(MD5Util.getMD5(newPassword1));
				int n = 0;
				if(newPassword1.equals(newPassword)){
					try {Class.forName("com.mysql.jdbc.Driver");
					Connection connection = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

						String sql = "update userinfo set userPassword = ? where userAccount = ?";
						PreparedStatement ps = connection.prepareStatement(sql);
						ps.setObject(1, newPassword);
						ps.setObject(2, e_account);
						n = ps.executeUpdate();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(n>0){
						JOptionPane.showMessageDialog(null, "�޸�����ɹ���");
						RevisePassword_e.this.dispose();
						MainView_User mainView_User = new MainView_User(e_account);
					}else{
						JOptionPane.showMessageDialog(null, "�޸�����ʧ�ܣ�");
						MainView_User mainView_User = new MainView_User(e_account);
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "�������벻һ�£������²�����");
					RevisePassword_e.this.dispose();
					MainView_User mainView_User = new MainView_User(e_account);
				}
				
				
				
			}

		});

		// �˳���ť
		exitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "ȷ���˳���", "��ʾ",
						JOptionPane.YES_NO_OPTION);

				if (n == 0) {
					JOptionPane.showMessageDialog(null, "�˳��ɹ���ף������˳����");
					System.exit(0);
				} else {
					return;
				}

			}

		});

		// �����ť
		clearButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				newpasswordField.setText("");

			}

		});

		this.addWindowListener(new WindowListener());
		this.setVisible(true);

	}

}
