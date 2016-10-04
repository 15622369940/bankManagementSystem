package view_ATM;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import util.MD5Util;

public class CurrentTransfer_2 extends JDialog {

	private JLabel kahaoLabel = new JLabel("������ת�˽�");
	private JLabel tishiLabel = new JLabel("�������������룺");

	private JTextField kahaoField = new JTextField();
	private JTextField tishiField = new JPasswordField();

	private JButton giveupButton = new JButton("��������");
	private JButton continueButton = new JButton("ȷ��");

	private Double amount_ = null;
	private Double amount_2 = null;
	private String password = null;

	public CurrentTransfer_2(final String jaccount, final String paccount) {

		this.setLayout(null); // ���ַ�ʽ�����Բ���
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		kahaoLabel.setBounds(80, 60, 190, 30);
		add(kahaoLabel);
		kahaoLabel.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		kahaoField.setBounds(270, 60, 190, 30);
		add(kahaoField);

		tishiLabel.setBounds(80, 130, 190, 30);
		add(tishiLabel);
		tishiLabel.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		tishiField.setBounds(270, 130, 190, 30);
		add(tishiField);

		giveupButton.setBounds(80, 230, 90, 50);
		add(giveupButton);
		continueButton.setBounds(420, 230, 90, 50);
		add(continueButton);

		// double amount = Double.parseDouble(kahaoField.getText());

		// String password = tishiField.getText();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/Bank_Management_System", "root",
					"admin");
			String sql = "select balance from currentAccount where userAccount = ? ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setObject(1, jaccount);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				amount_ = rs.getDouble(1);
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/Bank_Management_System", "root",
					"admin");

			String sql1 = "select balance from currentAccount where userAccount = ? ";
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setObject(1, paccount);
			ResultSet rs = ps1.executeQuery();
			if (rs.next()) {
				amount_2 = rs.getDouble(1);
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		giveupButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				CurrentTransfer_2.this.dispose();
				MainView_User mainView_User = new MainView_User(jaccount);

			}

		});

		continueButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// �жϽ���Ƿ񳬳������ж�����

				double amount = Double.parseDouble(kahaoField.getText());
				String password_ = tishiField.getText();
				password_ = MD5Util.getMD5(MD5Util.getMD5(password_));
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection6 = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1/Bank_Management_System", "root",
							"admin");

					String sql1 = "select userPassword from userinfo where userAccount = ? ";
					PreparedStatement ps1 = connection6.prepareStatement(sql1);
					ps1.setObject(1, paccount);
					ResultSet rs = ps1.executeQuery();
					if (rs.next()) {
						password = rs.getString(1);
					}
				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				System.out.println(amount_);
				
				if (amount > amount_) {
					JOptionPane.showMessageDialog(null, "�˻����㣬�޷�ת��");
					kahaoField.setText("");
					return;
				}
				if (password.equals(password_)) {
					// һ�߼�һ�߼�

					int n = 0;
					int n1 = 0;

					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection connection = DriverManager
								.getConnection(
										"jdbc:mysql://127.0.0.1/Bank_Management_System",
										"root", "admin");

						String sql = "update currentAccount set balance = ? where userAccount = ?";
						PreparedStatement ps2 = connection
								.prepareStatement(sql);
						ps2.setObject(1, amount_ - amount);
						ps2.setObject(2, jaccount);
						n = ps2.executeUpdate();

						String sql2 = "update currentAccount set balance = ? where userAccount = ?";
						PreparedStatement ps3 = connection
								.prepareStatement(sql2);
						ps3.setObject(1, amount_2 + amount);
						ps3.setObject(2, paccount);

						n1 = ps3.executeUpdate();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (n > 0 && n1 > 0) {
						JOptionPane.showMessageDialog(null, "ת���ɹ���");
						CurrentTransfer_2.this.dispose();
						JOptionPane.showMessageDialog(null, "���ĵ�ǰ���Ϊ��"
								+ (amount_ - amount));
						MainView_User mainView_User = new MainView_User(
								jaccount);
					}

				} else {
					JOptionPane.showMessageDialog(null, "�������");
					tishiField.setText("");
					return;
				}

			}

		});

		this.setTitle("ת��ҵ��");
		this.setResizable(false); // �����϶������
		this.setSize(550, 370);
		this.setLocationRelativeTo(null); // ����
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // �رգ���ʲô������
		this.setVisible(true); // ������ӻ�

	}

	public static void main(String[] args) {
		CurrentTransfer_2 currentTransfer_2 = new CurrentTransfer_2(
				"350964661", "867110115");
	}

}
