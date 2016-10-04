package view_ATM;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import pojo.DespositType;

public class Desposit_Main extends JDialog {

	private Double amount = null;

	private JComboBox typeBox = new JComboBox();

	private JLabel tishi1Label = new JLabel("�������");
	private JLabel tishi2Label = new JLabel("��ѡ�������ͣ�");

	private JTextField jeField = new JTextField();

	private JRadioButton hqButton = new JRadioButton("����");
	private JRadioButton dqButton = new JRadioButton("����");

	private JButton giveupButton = new JButton("��������");
	private JButton yesButton = new JButton("ȷ�ϴ��");

	@SuppressWarnings("unchecked")
	public Desposit_Main(final String account) {

		this.setLayout(null); // ���ַ�ʽ�����Բ���
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		final DecimalFormat df = new DecimalFormat("0.00");
		typeBox.setBounds(380, 130, 110, 30);
		add(typeBox);

		tishi1Label.setBounds(80, 60, 120, 30);
		add(tishi1Label);
		tishi2Label.setBounds(80, 130, 120, 30);
		add(tishi2Label);

		jeField.setBounds(220, 60, 160, 30);
		add(jeField);

		hqButton.setBounds(220, 130, 70, 30);
		dqButton.setBounds(305, 130, 70, 30);

		ButtonGroup group = new ButtonGroup();
		group.add(hqButton);
		group.add(dqButton);
		add(hqButton);
		add(dqButton);

		hqButton.setSelected(true);

		giveupButton.setBounds(60, 200, 90, 35);
		add(giveupButton);
		yesButton.setBounds(410, 200, 90, 35);
		add(yesButton);

		typeBox.setEnabled(false);

		// �����ݿ�Ѷ��ڵ�����Ū��ȥ�����б��
		try {Class.forName("com.mysql.jdbc.Driver");
			Connection connection2 = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");
			String sql2 = "select typename from DespositType";
			PreparedStatement ps2 = connection2.prepareStatement(sql2);
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next()) {
				DespositType despositType = new DespositType();
				despositType.setTypename(rs2.getString(1));
				typeBox.addItem(despositType);

			}
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		dqButton.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				if (dqButton.isSelected()) {
					typeBox.setEnabled(true);
				}

			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		hqButton.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				if (hqButton.isSelected()) {
					typeBox.setEnabled(false);
				}

			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		// �������ݿ⣬��Ǯ������
		try {Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

			String sql = "select balance from currentAccount where userAccount = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setObject(1, account);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				amount = rs.getDouble(1);

			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		yesButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (jeField.getText() == null || jeField.getText() == "") {
					JOptionPane.showMessageDialog(null, "�������Ϊ�գ�");
					return;
				}

				int n = 0;
				if (hqButton.isSelected()) {
					if (jeField.getText() == null || "".equals(jeField.getText())) {
						JOptionPane.showMessageDialog(null, "�������Ϊ�գ�");
						return;
					}
					int jine1 = Integer.parseInt(jeField.getText());
					if (jine1 % 100 != 0) {
						JOptionPane.showMessageDialog(null, "������ֻ��Ϊ100�ı�����");
						jeField.setText("");
						return;
					}
					double zongqian = amount + Double.parseDouble(jine1+"");
					try {
						// �����ݿ⣬����Ǯ
						Class.forName("com.mysql.jdbc.Driver");
						Connection connection = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

						String sql = "update currentAccount set balance = ? where userAccount = ?";
						PreparedStatement ps = connection.prepareStatement(sql);
						ps.setObject(1, zongqian);
						ps.setObject(2, account);
						n = ps.executeUpdate();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (n > 0) {
						jeField.setText("");
						JOptionPane.showMessageDialog(null, "���ɹ���");
						JOptionPane.showMessageDialog(null, "����ǰ�˻������Ϊ��"
								+ (df.format(amount + jine1)));

						MainView_User mainView_User = new MainView_User(account);
						Desposit_Main.this.dispose();
					} else {
						jeField.setText("");
						JOptionPane.showMessageDialog(null, "���ʧ�ܣ�");
						return;
					}

				}
				if (dqButton.isSelected()) {

					if (jeField.getText() == null || jeField.getText() == "") {
						JOptionPane.showMessageDialog(null, "�������Ϊ�գ�");
						return;
					}

					int jine2 = Integer.parseInt(jeField.getText());
					if (jine2 % 100 != 0) {
						JOptionPane.showMessageDialog(null, "�����ֻ��Ϊ100�ı�����");
						jeField.setText("");
						return;
					}
					String s = typeBox.getSelectedItem().toString();
					if ("�����¶���".equals(s)) {
						// �����ݿ��ж��Ƿ��Ѿ����ڸ��˻�
						try {Class.forName("com.mysql.jdbc.Driver");
						Connection connection4 = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

							String sql4 = "select userAccount from fixedAccount where userAccount = ?";
							PreparedStatement ps4 = connection4
									.prepareStatement(sql4);
							ps4.setObject(1, account);
							ResultSet rs = ps4.executeQuery();
							if (rs.next()) {
								JOptionPane.showMessageDialog(null,
										"���˻��Ѵ��ڶ��ڴ��޷��ٴδ��룡");

								return;
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						// ʱ��
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd ");
						Date date1 = new Date();

						java.sql.Date date = null;
						date = new java.sql.Date(date1.getTime());

						

						double interest_ = jine2*0.25*0.016;
						int y=0;
						// �����ݿ⣬��ʱ��Ҳд��ȥ �½�����û�
						try {Class.forName("com.mysql.jdbc.Driver");
						Connection connection5 = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

							String sql5 = "insert into fixedAccount(useraccount,balance,interest,type,tip,time) values(?,?,?,?,?,?) ";
							PreparedStatement ps5 = connection5.prepareStatement(sql5);
							ps5.setObject(1, account);
							ps5.setObject(2, jine2);
							ps5.setObject(3, interest_);
							ps5.setObject(4, 1);
							ps5.setObject(5, 0);
							ps5.setObject(6, date);
							
							y=ps5.executeUpdate();
							if(y>0){
								JOptionPane.showMessageDialog(null, "��Ӷ��ڴ��ɹ���");
								JOptionPane.showMessageDialog(null, "�����º�������õ���ϢΪ��"+interest_);
								Desposit_Main.this.dispose();
								MainView_User mainView_User = new MainView_User(account);
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} 
					if ("���궨��".equals(s)) {
						// �����ݿ��ж��Ƿ��Ѿ����ڸ��˻�
						try {Class.forName("com.mysql.jdbc.Driver");
						Connection connection4 = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

							String sql4 = "select userAccount from fixedAccount where userAccount = ?";
							PreparedStatement ps4 = connection4
									.prepareStatement(sql4);
							ps4.setObject(1, account);
							ResultSet rs = ps4.executeQuery();
							if (rs.next()) {
								JOptionPane.showMessageDialog(null,
										"���˻��Ѵ��ڶ��ڴ��޷��ٴδ��룡");

								return;
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						// ʱ��
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd ");
						Date date1 = new Date();

						java.sql.Date date = null;
						date = new java.sql.Date(date1.getTime());

						

						double interest_ = jine2*0.5*0.018;
						int y=0;
						// �����ݿ⣬��ʱ��Ҳд��ȥ �½�����û�
						try {
							Class.forName("com.mysql.jdbc.Driver");
							Connection connection5 = DriverManager.getConnection(
									"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

							String sql5 = "insert into fixedAccount(useraccount,balance,interest,type,tip,time) values(?,?,?,?,?,?) ";
							PreparedStatement ps5 = connection5.prepareStatement(sql5);
							ps5.setObject(1, account);
							ps5.setObject(2, jine2);
							ps5.setObject(3, interest_);
							ps5.setObject(4, 1);
							ps5.setObject(5, 0);
							ps5.setObject(6, date);
							
							y=ps5.executeUpdate();
							if(y>0){
								JOptionPane.showMessageDialog(null, "��Ӷ��ڴ��ɹ���");
								JOptionPane.showMessageDialog(null, "�����º�������õ���ϢΪ��"+interest_);
								Desposit_Main.this.dispose();
								MainView_User mainView_User = new MainView_User(account);
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} 
					if ("һ�궨��".equals(s)) {
						// �����ݿ��ж��Ƿ��Ѿ����ڸ��˻�
						try {Class.forName("com.mysql.jdbc.Driver");
						Connection connection4 = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

							String sql4 = "select userAccount from fixedAccount where userAccount = ?";
							PreparedStatement ps4 = connection4
									.prepareStatement(sql4);
							ps4.setObject(1, account);
							ResultSet rs = ps4.executeQuery();
							if (rs.next()) {
								JOptionPane.showMessageDialog(null,
										"���˻��Ѵ��ڶ��ڴ��޷��ٴδ��룡");

								return;
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						// ʱ��
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd ");
						Date date1 = new Date();

						java.sql.Date date = null;
						date = new java.sql.Date(date1.getTime());

						

						double interest_ = jine2*1*0.020;
						int y=0;
						// �����ݿ⣬��ʱ��Ҳд��ȥ �½�����û�
						try {
							Class.forName("com.mysql.jdbc.Driver");
							Connection connection5 = DriverManager.getConnection(
									"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

							String sql5 = "insert into fixedAccount(useraccount,balance,interest,type,tip,time) values(?,?,?,?,?,?) ";
							PreparedStatement ps5 = connection5.prepareStatement(sql5);
							ps5.setObject(1, account);
							ps5.setObject(2, jine2);
							ps5.setObject(3, interest_);
							ps5.setObject(4, 1);
							ps5.setObject(5, 0);
							ps5.setObject(6, date);
							
							y=ps5.executeUpdate();
							if(y>0){
								JOptionPane.showMessageDialog(null, "��Ӷ��ڴ��ɹ���");
								JOptionPane.showMessageDialog(null, "�����º�������õ���ϢΪ��"+interest_);
								Desposit_Main.this.dispose();
								MainView_User mainView_User = new MainView_User(account);
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} 
					if ("���궨��".equals(s)) {
						// �����ݿ��ж��Ƿ��Ѿ����ڸ��˻�
						try {Class.forName("com.mysql.jdbc.Driver");
						Connection connection4 = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

							String sql4 = "select userAccount from fixedAccount where userAccount = ?";
							PreparedStatement ps4 = connection4
									.prepareStatement(sql4);
							ps4.setObject(1, account);
							ResultSet rs = ps4.executeQuery();
							if (rs.next()) {
								JOptionPane.showMessageDialog(null,
										"���˻��Ѵ��ڶ��ڴ��޷��ٴδ��룡");

								return;
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						// ʱ��
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd ");
						Date date1 = new Date();

						java.sql.Date date = null;
						date = new java.sql.Date(date1.getTime());

						

						double interest_ = jine2*2*0.026;
						int y=0;
						// �����ݿ⣬��ʱ��Ҳд��ȥ �½�����û�
						try {Class.forName("com.mysql.jdbc.Driver");
						Connection connection5 = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

							String sql5 = "insert into fixedAccount(useraccount,balance,interest,type,tip,time) values(?,?,?,?,?,?) ";
							PreparedStatement ps5 = connection5.prepareStatement(sql5);
							ps5.setObject(1, account);
							ps5.setObject(2, jine2);
							ps5.setObject(3, interest_);
							ps5.setObject(4, 1);
							ps5.setObject(5, 0);
							ps5.setObject(6, date);
							
							y=ps5.executeUpdate();
							if(y>0){
								JOptionPane.showMessageDialog(null, "��Ӷ��ڴ��ɹ���");
								JOptionPane.showMessageDialog(null, "�����º�������õ���ϢΪ��"+interest_);
								Desposit_Main.this.dispose();
								MainView_User mainView_User = new MainView_User(account);
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} 
					if ("���궨��".equals(s)) {
						// �����ݿ��ж��Ƿ��Ѿ����ڸ��˻�
						try {Class.forName("com.mysql.jdbc.Driver");
						Connection connection4 = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

							String sql4 = "select userAccount from fixedAccount where userAccount = ?";
							PreparedStatement ps4 = connection4
									.prepareStatement(sql4);
							ps4.setObject(1, account);
							ResultSet rs = ps4.executeQuery();
							if (rs.next()) {
								JOptionPane.showMessageDialog(null,
										"���˻��Ѵ��ڶ��ڴ��޷��ٴδ��룡");

								return;
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						// ʱ��
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd ");
						Date date1 = new Date();

						java.sql.Date date = null;
						date = new java.sql.Date(date1.getTime());

						

						double interest_ = jine2*3*0.0325;
						int y=0;
						// �����ݿ⣬��ʱ��Ҳд��ȥ �½�����û�
						try {Class.forName("com.mysql.jdbc.Driver");
						Connection connection5 = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

							String sql5 = "insert into fixedAccount(useraccount,balance,interest,type,tip,time) values(?,?,?,?,?,?) ";
							PreparedStatement ps5 = connection5.prepareStatement(sql5);
							ps5.setObject(1, account);
							ps5.setObject(2, jine2);
							ps5.setObject(3, interest_);
							ps5.setObject(4, 1);
							ps5.setObject(5, 0);
							ps5.setObject(6, date);
							
							y=ps5.executeUpdate();
							if(y>0){
								JOptionPane.showMessageDialog(null, "��Ӷ��ڴ��ɹ���");
								JOptionPane.showMessageDialog(null, "�����º�������õ���ϢΪ��"+interest_);
								Desposit_Main.this.dispose();
								MainView_User mainView_User = new MainView_User(account);
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} 

				}

			}

		});

		giveupButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Desposit_Main.this.dispose();
				MainView_User mainView_User = new MainView_User(account);

			}

		});

		// ��Ⱦ��
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);

		this.setTitle("���");
		this.setResizable(false); // �����϶������
		this.setSize(550, 330);
		this.setLocationRelativeTo(null); // ����
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // �رգ���ʲô������
		this.setVisible(true); // ������ӻ�
	}

	public static void main(String[] args) {
		Desposit_Main desposit_Main = new Desposit_Main("350964661");
	}
}
