package view_ATM;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class CurrentTransfer_Main extends JDialog {

	private JLabel kahaoLabel = new JLabel("������Է����ţ�");
	private JLabel tishiLabel = new JLabel("��ʾ��");

	private String name1 = null;

	private JTextField kahaoField = new JTextField();
	private JTextField tishiField = new JTextField();

	private JButton giveupButton = new JButton("��������");
	private JButton continueButton = new JButton("��������");

	public CurrentTransfer_Main(final String account) {

		this.setLayout(null); // ���ַ�ʽ�����Բ���
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		tishiField.setEditable(false);

		int a = 0;

		kahaoLabel.setBounds(80, 60, 190, 30);
		add(kahaoLabel);
		kahaoLabel.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		kahaoField.setBounds(270, 60, 190, 30);
		add(kahaoField);

		tishiLabel.setBounds(110, 130, 100, 30);
		add(tishiLabel);
		tishiLabel.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		tishiField.setBounds(265, 130, 190, 40);
		add(tishiField);

		giveupButton.setBounds(80, 230, 90, 50);
		add(giveupButton);
		continueButton.setBounds(420, 230, 90, 50);
		add(continueButton);

		kahaoField.setText("6217002940103996052");

		kahaoField.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			public void keyReleased(KeyEvent e) {
				if (kahaoField.getText() == null
						|| "".equals(kahaoField.getText())) {
					JOptionPane.showMessageDialog(null, "���뿨�Ų���Ϊ�գ�");
					return;
				} else if (kahaoField.getText().length() == 19) {
					final String id = kahaoField.getText();
					// �����ݿ� �ҳ��⿨�Ŷ�Ӧ������
					try {Class.forName("com.mysql.jdbc.Driver");
					Connection connection = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

						String sql = "select userName from userinfo where userAccount = ? ";
						PreparedStatement ps = connection.prepareStatement(sql);
						ps.setObject(1, id);
						ResultSet rs = ps.executeQuery();

						if (rs.next()) {
							name1 = (String) rs.getObject(1);
							tishiField.setText((String) name1);
							continueButton.setEnabled(true);
							// ��������
							continueButton
									.addActionListener(new ActionListener() {

										public void actionPerformed(
												ActionEvent e) {

											CurrentTransfer_Main.this.dispose();
	
											CurrentTransfer_2 currentTransfer_2 = new CurrentTransfer_2(
													account, id);

										}

									});

						} else {
							JOptionPane.showMessageDialog(null, "�ÿ��Ų����ڣ�");
							kahaoField.setText("");
							return;
						}
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (kahaoField.getText().length() > 19) {
					JOptionPane.showMessageDialog(null, "����Ϊ19λ");
					tishiField.setText("");
					kahaoField.setText("");
					return;
				}

			}

			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		giveupButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				CurrentTransfer_Main.this.dispose();
				MainView_User mainView_User = new MainView_User(account);

			}

		});
		//��������
		continueButton.setEnabled(false);
		this.setTitle("ת��ҵ��");
		this.setResizable(false); // �����϶������
		this.setSize(550, 370);
		this.setLocationRelativeTo(null); // ����
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // �رգ���ʲô������
		this.setVisible(true); // ������ӻ�
	}

	public static void main(String[] args) {
		CurrentTransfer_Main currentTransfer_Main = new CurrentTransfer_Main(
				"350964661");
	}
}
