package view_Counter;

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
import javax.swing.JTextField;

import view_Admin.MainView_Admin;


public class ForgetPassword_counter extends JDialog {

	private JLabel tishiLabel = new JLabel("������������Ϣ");

	private JLabel l1 = new JLabel("���ţ�");
	private JLabel l2 = new JLabel("�绰���룺");
	private JLabel l3 = new JLabel("���֤�ţ�");
	private JLabel l4 = new JLabel("����ţ�");

	private JTextField accountField = new JTextField();
	private JTextField telField = new JTextField();
	private JTextField idField = new JTextField();
	private JTextField mailField = new JTextField();

	private JButton fhButton = new JButton("����");
	private JButton qrButton = new JButton("ȷ��");

	public ForgetPassword_counter(final String account_admin) {
		this.setLayout(null); // ���ַ�ʽ�����Բ���

		JOptionPane.showMessageDialog(null, "������������Ϣ");

		l1.setBounds(80, 40, 80, 35);
		l2.setBounds(80, 100, 80, 35);
		l3.setBounds(80, 160, 80, 35);
		l4.setBounds(80, 220, 80, 35);
		fhButton.setBounds(70, 300, 70, 30);
		qrButton.setBounds(320, 300, 70, 30);

		accountField.setBounds(200, 40, 180, 30);
		telField.setBounds(200, 100, 180, 30);
		idField.setBounds(200, 160, 180, 30);
		mailField.setBounds(200, 220, 180, 30);

		add(l1);
		add(l2);
		add(l3);
		add(l4);

		add(accountField);
		add(telField);
		add(idField);
		add(mailField);

		add(fhButton);
		add(qrButton);

		qrButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (accountField.getText() == null
						|| "".equals(accountField.getText())
						|| telField.getText() == null
						|| "".equals(telField.getText())
						|| idField.getText() == null
						|| "".equals(idField.getText())
						|| mailField.getText() == null
						|| "".equals(mailField.getText())) {

					JOptionPane.showMessageDialog(null, "�������ݲ���Ϊ�գ�");
					return;

				}

				String account = accountField.getText();
				String tel = telField.getText();
				String id = idField.getText();
				String mail = mailField.getText();

				// �����ݿ⣬�ж���Ϣ�Ƿ���ȣ��������ԭ����
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

					String sql = "select userPassword,userTel,userID,userMail from userinfo where userAccount = ?";
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setObject(1, account);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						String password = rs.getObject(1).toString();
						String ttel = rs.getObject(2).toString();
						String tid = rs.getObject(3).toString();
						String tmail = rs.getObject(4).toString();

						if (tel.equals(ttel) && id.equals(tid)
								&& mail.equals(tmail)) {
							JOptionPane.showMessageDialog(null,
									"�����֤�ɹ������μ��������룡");
							JOptionPane.showMessageDialog(null, "��������Ϊ��"
									+ password);
							ForgetPassword_counter.this.dispose();
							MainView_Admin mainView_Admin = new MainView_Admin(
									account_admin);
						} else {
							JOptionPane.showMessageDialog(null, "�����֤ʧ�ܣ�");
							return;
						}

					}
					JOptionPane.showMessageDialog(null, "�����֤ʧ�ܣ�");

				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		fhButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				ForgetPassword_counter.this.dispose();
				MainView_Admin mainView_Admin = new MainView_Admin(
						account_admin);

			}

		});

		this.setTitle("�һ�����");
		this.setResizable(false); // �����϶������
		this.setSize(460, 400);
		this.setLocationRelativeTo(null); // ����
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // �رգ���ʲô������
		this.setVisible(true); // ������ӻ�

	}

	public static void main(String[] args) {
		ForgetPassword_counter forgetPassword_counter = new ForgetPassword_counter(
				"613258200");
	}

}
