package view_Counter;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import view_ATM.AddSpecialDraw;
import view_ATM.Draw_main;
import view_ATM.MainView_User;
import view_Admin.MainView_Admin;


public class Draw_main_counter extends JDialog{

	double shijian = 0;
	private double ninterest = 0;
	private double balance = 0;
	private double balance_ = 0;

	private JLabel etishiLabel = new JLabel("Please    Select    Service");
	private JLabel ctishiLabel = new JLabel("��ѡ������ҵ��");
	private JLabel ctishi1Label = new JLabel("��ȡ����");

	private JButton b1 = new JButton("100");
	private JButton b2 = new JButton("200");
	private JButton b3 = new JButton("500");
	private JButton b4 = new JButton("1000");
	private JButton b5 = new JButton("2000");
	private JButton b6 = new JButton("5000");

	private JButton dqButton = new JButton("����ȡ��");
	private JButton spButton = new JButton("������Լȡ��");
	private JButton fhButton = new JButton("����");
	private JButton qrButton = new JButton("ȷ��");

	private JTextField jeField = new JTextField();

	public Draw_main_counter(final String account_,final String account_admin) {

		this.setLayout(null); // ���ַ�ʽ�����Բ���
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		etishiLabel.setBounds(190, 20, 500, 45);
		add(etishiLabel);
		etishiLabel.setFont(new Font("", Font.PLAIN, 33));
		etishiLabel.setForeground(Color.black);

		ctishiLabel.setBounds(300, 60, 350, 40);
		ctishiLabel.setForeground(Color.black);
		add(ctishiLabel);
		ctishiLabel.setFont(new Font("�п�", Font.PLAIN, 22));
		ctishi1Label.setBounds(330, 120, 350, 40);
		ctishi1Label.setForeground(Color.black);
		add(ctishi1Label);
		ctishi1Label.setFont(new Font("�п�", Font.PLAIN, 22));

		jeField.setBounds(310, 170, 150, 60);
		jeField.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		add(jeField);

		b1.setBounds(110, 150, 140, 40);
		b1.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		add(b1);
		b2.setBounds(110, 210, 140, 40);
		b2.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		add(b2);
		b3.setBounds(110, 270, 140, 40);
		b3.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		add(b3);
		dqButton.setBounds(90, 330, 190, 40);
		dqButton.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		add(dqButton);
		spButton.setBounds(90, 390, 190, 40);
		spButton.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		add(spButton);

		b4.setBounds(500, 150, 140, 40);
		b4.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		add(b4);
		b5.setBounds(500, 210, 140, 40);
		b5.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		add(b5);
		b6.setBounds(500, 270, 140, 40);
		b6.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		add(b6);
		fhButton.setBounds(480, 330, 190, 40);
		fhButton.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		add(fhButton);
		qrButton.setBounds(480, 390, 190, 40);
		qrButton.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		add(qrButton);

		final String account = account_;
		// �����ݿ��ȡ��Ӧ��Ϣ
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

			String sql = "select balance,interest,operateTime from currentAccount where userAccount = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setObject(1, account);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {

				balance = resultSet.getDouble(1);
				double interest = resultSet.getDouble(2);
				Date time = resultSet.getDate(3);
				Calendar calendar = Calendar.getInstance();
				java.util.Date now = new java.util.Date();
				calendar.setTime(now);
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH) + 1;
				int day = calendar.get(calendar.DAY_OF_MONTH);
				calendar.setTime(time);
				int oyear = calendar.get(Calendar.YEAR);
				int omonth = calendar.get(Calendar.MONTH) + 1;
				int oday = calendar.get(Calendar.DAY_OF_MONTH);

				int yshijian = year - oyear;
				int dshijian = 0;

				int dshijian1 = month * 30 + day;
				int dshijian2 = omonth * 30 + oday;

				if (dshijian1 - dshijian2 < 0) {
					dshijian = dshijian2 - dshijian1;
					yshijian = yshijian - 1;
				} else {
					dshijian = dshijian1 - dshijian2;
				}
				shijian = ((double) yshijian * 365 + (double) dshijian) / 365.0;

				ninterest = balance * shijian * 0.0035;

				balance_ = balance + ninterest;
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		b1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int n = 0;

				if (balance_ - 100 < 0) {
					JOptionPane.showMessageDialog(null, "����");
					return;
				}

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date1 = new java.util.Date();

				Date date = null;
				date = new Date(date1.getTime());

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection1 = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

					String sql1 = "update currentAccount set balance = ?,operateTime = ?,interest=? where userAccount = ?";
					PreparedStatement ps1 = connection1.prepareStatement(sql1);
					// ������Ϣ Ȼ���ʱ��ı䣬���ı䣬��Ϣ�ı䡣

					ps1.setObject(1, balance_ - 100);
					ps1.setObject(2, date);
					ps1.setObject(3, ninterest);
					ps1.setObject(4, account_);

					n = ps1.executeUpdate();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (n > 0) {
					JOptionPane.showMessageDialog(null, "ȡ��ɹ���");
					jeField.setText("");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "ȡ��ʧ�ܣ�");
					return;
				}

			}
		});

		b2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int n = 0;
				if (balance_ - 200 < 0) {
					JOptionPane.showMessageDialog(null, "����");
					return;
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date1 = new java.util.Date();

				Date date = null;
				date = new Date(date1.getTime());

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection1 = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

					String sql1 = "update currentAccount set balance = ?,operateTime = ?,interest=? where userAccount = ?";
					PreparedStatement ps1 = connection1.prepareStatement(sql1);
					// ������Ϣ Ȼ���ʱ��ı䣬���ı䣬��Ϣ�ı䡣

					ps1.setObject(1, balance_ - 200);
					ps1.setObject(2, date);
					ps1.setObject(3, ninterest);
					ps1.setObject(4, account_);

					n = ps1.executeUpdate();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (n > 0) {
					JOptionPane.showMessageDialog(null, "ȡ��ɹ���");
					jeField.setText("");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "ȡ��ʧ�ܣ�");
					return;
				}

			}

		});

		b3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int n = 0;
				if (balance_ - 500 < 0) {
					JOptionPane.showMessageDialog(null, "����");
					return;
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date1 = new java.util.Date();

				Date date = null;
				date = new Date(date1.getTime());

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection1 = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

					String sql1 = "update currentAccount set balance = ?,operateTime = ?,interest=? where userAccount = ?";
					PreparedStatement ps1 = connection1.prepareStatement(sql1);
					// ������Ϣ Ȼ���ʱ��ı䣬���ı䣬��Ϣ�ı䡣

					ps1.setObject(1, balance_ - 500);
					ps1.setObject(2, date);
					ps1.setObject(3, ninterest);
					ps1.setObject(4, account_);

					n = ps1.executeUpdate();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (n > 0) {
					JOptionPane.showMessageDialog(null, "ȡ��ɹ���");
					jeField.setText("");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "ȡ��ʧ�ܣ�");
					return;
				}

			}

		});

		b4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int n = 0;
				if (balance_ - 1000 < 0) {
					JOptionPane.showMessageDialog(null, "����");
					return;
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date1 = new java.util.Date();

				Date date = null;
				date = new Date(date1.getTime());

				try {Class.forName("com.mysql.jdbc.Driver");
				Connection connection1 = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

					String sql1 = "update currentAccount set balance = ?,operateTime = ?,interest=? where userAccount = ?";
					PreparedStatement ps1 = connection1.prepareStatement(sql1);
					// ������Ϣ Ȼ���ʱ��ı䣬���ı䣬��Ϣ�ı䡣

					ps1.setObject(1, balance_ - 1000);
					ps1.setObject(2, date);
					ps1.setObject(3, ninterest);
					ps1.setObject(4, account_);

					n = ps1.executeUpdate();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (n > 0) {
					JOptionPane.showMessageDialog(null, "ȡ��ɹ���");
					jeField.setText("");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "ȡ��ʧ�ܣ�");
					return;
				}

			}

		});

		b5.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int n = 0;
				if (balance_ - 2000 < 0) {
					JOptionPane.showMessageDialog(null, "����");
					return;
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date1 = new java.util.Date();

				Date date = null;
				date = new Date(date1.getTime());

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection1 = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

					String sql1 = "update currentAccount set balance = ?,operateTime = ?,interest=? where userAccount = ?";
					PreparedStatement ps1 = connection1.prepareStatement(sql1);
					// ������Ϣ Ȼ���ʱ��ı䣬���ı䣬��Ϣ�ı䡣

					ps1.setObject(1, balance_ - 2000);
					ps1.setObject(2, date);
					ps1.setObject(3, ninterest);
					ps1.setObject(4, account_);

					n = ps1.executeUpdate();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (n > 0) {
					JOptionPane.showMessageDialog(null, "ȡ��ɹ���");
					jeField.setText("");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "ȡ��ʧ�ܣ�");
					return;
				}

			}

		});

		b6.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int n = 0;
				if (balance_ - 5000 < 0) {
					JOptionPane.showMessageDialog(null, "����");
					return;
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date1 = new java.util.Date();

				Date date = null;
				date = new Date(date1.getTime());

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection1 = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

					String sql1 = "update currentAccount set balance = ?,operateTime = ?,interest=? where userAccount = ?";
					PreparedStatement ps1 = connection1.prepareStatement(sql1);
					// ������Ϣ Ȼ���ʱ��ı䣬���ı䣬��Ϣ�ı䡣

					ps1.setObject(1, balance_ - 5000);
					ps1.setObject(2, date);
					ps1.setObject(3, ninterest);
					ps1.setObject(4, account_);

					n = ps1.executeUpdate();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (n > 0) {
					JOptionPane.showMessageDialog(null, "ȡ��ɹ���");
					jeField.setText("");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "ȡ��ʧ�ܣ�");
					return;
				}

			}

		});

		spButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Draw_main_counter.this.dispose();
				AddSpecialDraw addSpecialDraw = new AddSpecialDraw(account_);

			}

		});

		// ���ذ�ť
		fhButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String account = account_;
				Draw_main_counter.this.dispose();
				MainView_Admin mainView_User = new MainView_Admin(account);

			}

		});

		// ȷ�ϰ�ť
		qrButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int n = 0;
				if (jeField.getText() == null || "".equals(jeField.getText().trim())) {

					jeField.setText("");
					JOptionPane.showMessageDialog(null, "����ȡ��ֵ����Ϊ�գ�");
					return;

				}
				int s = Integer.parseInt(jeField.getText());


				if (balance_ - s < 0) {
					JOptionPane.showMessageDialog(null, "����");
					return;
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date1 = new java.util.Date();

				Date date = null;
				date = new Date(date1.getTime());

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection1 = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

					String sql1 = "update currentAccount set balance = ?,operateTime = ?,interest=? where userAccount = ?";
					PreparedStatement ps1 = connection1.prepareStatement(sql1);
					// ������Ϣ Ȼ���ʱ��ı䣬���ı䣬��Ϣ�ı䡣

					ps1.setObject(1, balance_ - s);
					ps1.setObject(2, date);
					ps1.setObject(3, ninterest);
					ps1.setObject(4, account_);

					n = ps1.executeUpdate();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (n > 0) {
					JOptionPane.showMessageDialog(null, "ȡ��ɹ���");
					jeField.setText("");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "ȡ��ʧ�ܣ�");
					return;
				}

			}

		});

		this.setTitle("ȡ��");
		this.setResizable(false); // �����϶������
		this.setSize(730, 500);
		this.setLocationRelativeTo(null); // ����
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // �رգ���ʲô������
		this.setVisible(true); // ������ӻ�
	
	
}
}