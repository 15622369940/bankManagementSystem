package view_Counter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import view_Admin.MainView_Admin;


public class Revise_info extends JDialog {

	private JLabel l1 = new JLabel("����");
	private JLabel l2 = new JLabel("������");
	private JLabel l3 = new JLabel("�ٴ���������");
	private JLabel l4 = new JLabel("�Ա�");
	private JLabel l5 = new JLabel("����");
	private JLabel l6 = new JLabel("���֤��");
	private JLabel l7 = new JLabel("����");
	private JLabel l8 = new JLabel();
	private JLabel l9 = new JLabel("�绰");

	private JRadioButton mButton = new JRadioButton("��");
	private JRadioButton fButton = new JRadioButton("Ů");

	private JTextField mimaField = new JPasswordField();
	private JTextField mima2Field = new JPasswordField();

	private JTextField nameField = new JTextField();
	private JTextField idField = new JTextField();
	private JTextField mailField = new JTextField();
	private JTextField telField = new JTextField();

	private JButton qxButton = new JButton("ȡ��");
	private JButton qdButton = new JButton("ȷ��");

	public Revise_info(final String account_admin, final String account_user) {

		this.setLayout(null); // ���ַ�ʽ�����Բ���
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		l1.setBounds(80, 40, 80, 35);
		l2.setBounds(80, 95, 80, 35);
		l3.setBounds(80, 150, 80, 35);
		l4.setBounds(80, 205, 80, 35);
		l5.setBounds(80, 260, 80, 35);
		l6.setBounds(80, 315, 80, 35);
		l7.setBounds(80, 370, 80, 35);
		l9.setBounds(80, 425, 80, 35);

		l8.setBounds(200, 40, 200, 35);
		mimaField.setBounds(200, 95, 180, 30);
		mima2Field.setBounds(200, 150, 180, 30);

		mButton.setBounds(210, 205, 70, 30);
		fButton.setBounds(290, 205, 70, 30);

		ButtonGroup group = new ButtonGroup();
		group.add(mButton);
		group.add(fButton);
		add(mButton);
		add(fButton);

		nameField.setBounds(200, 260, 180, 30);
		idField.setBounds(200, 315, 180, 30);
		mailField.setBounds(200, 370, 180, 30);
		telField.setBounds(200, 425, 180, 35);

		nameField.setEditable(false);
		idField.setEditable(false);

		qxButton.setBounds(60, 490, 80, 30);
		qdButton.setBounds(330, 490, 80, 30);

		add(l1);
		add(l2);
		add(l3);
		add(l4);
		add(l5);
		add(l6);
		add(l6);
		add(l7);
		add(l9);

		add(l8);
		add(mimaField);
		add(mima2Field);
		add(qxButton);
		add(qdButton);
		add(nameField);
		add(idField);
		add(mailField);
		add(telField);

		l8.setText(account_user);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

			String sql = "select userID,userSex,userName from userinfo where userAccount =?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setObject(1, account_user);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String tid = rs.getObject(1).toString();
				String tsex = rs.getObject(2).toString();
				String tname = rs.getObject(3).toString();

				idField.setText(tid);
				nameField.setText(tname);
				if ("��".equals(tsex)) {
					mButton.setSelected(true);
				} else {
					fButton.setSelected(true);
				}

			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		qdButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (mimaField.getText() == null
						|| "".equals(mimaField.getText().trim())
						|| mima2Field.getText() == null
						|| "".equals(mima2Field.getText().trim())
						|| telField.getText() == null
						|| "".equals(telField.getText())
						|| mailField.getText() == null
						|| "".equals(mailField.getText())) {
					JOptionPane.showMessageDialog(null, "�������ݲ���Ϊ�գ�");
					return;
				}

				// �ж�����������������Ƿ����
				String password1 = mimaField.getText();
				String password2 = mima2Field.getText();
				String tel = telField.getText();
				String mail = mailField.getText();
				

				if (password1.equals(password2)) {
					int n = 0;
					int c = 0;
					// �����ݿ��޸���Ϣ
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection connection = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

						String sql2 = "update userinfo set userPassword=?,userTel = ?,userMail=? where userAccount = ?";

						PreparedStatement ps2 = connection.prepareStatement(sql2);
						ps2.setObject(1, password1);
						ps2.setObject(2, tel);
						ps2.setObject(3, mail);
						ps2.setObject(4, account_user);
						
						n=ps2.executeUpdate();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(n>0){
						JOptionPane.showMessageDialog(null, "�޸��û���Ϣ�ɹ���");
						Revise_info.this.dispose();
						MainView_Admin mainView_Admin = new MainView_Admin(account_admin);
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "�����������벻һ�£������²�����");
					mimaField.setText("");
					mima2Field.setText("");
					nameField.setText("");
					idField.setText("");
					mailField.setText("");
					return;
				}

			}

		});

		qxButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Revise_info.this.dispose();
				MainView_Admin mainView_Admin = new MainView_Admin(
						account_admin);

			}

		});

		this.setTitle("�޸��û���Ϣ");
		this.setResizable(false); // �����϶������
		this.setSize(460, 580);
		this.setLocationRelativeTo(null); // ����
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // �رգ���ʲô������
		this.setVisible(true); // ������ӻ�
	}

	public static void main(String[] args) {
		Revise_info revise_info = new Revise_info("613258200", "350964661");
	}

}
