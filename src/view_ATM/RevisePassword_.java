package view_ATM;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import method.WindowListener;

public class RevisePassword_ extends JDialog {

	private JLabel tishiLabel = new JLabel("�����������������ȷ�ϼ�");
	private JTextField newpasswordField = new JPasswordField();
	private JButton yesButton = new JButton("ȷ��");
	private JLabel jLabel = new JLabel("");
	private JButton exitButton = new JButton("����");
	private JButton clearButton = new JButton("���");

	public RevisePassword_(final String account) {

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

		yesButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String newPassword = newpasswordField.getText();

				int num = 0;

				int letter = 0;
				int dig = 0;
				int other = 0;

				for (int i = 0; i < newPassword.length(); i++) {
					if (newPassword.charAt(i) >= 97
							&& newPassword.charAt(i) <= 122
							|| newPassword.charAt(i) >= 65
							&& newPassword.charAt(i) <= 90) {
						letter = 1;
					}

					else if (newPassword.charAt(i) >= 48
							&& newPassword.charAt(i) <= 57) {
						dig = 1;
					}

					else {
						other = 1;

					}

				}

				num = letter + dig + other;

				if (newPassword.length() < 6) {
					JOptionPane.showMessageDialog(null, "���볤��Ҫ��Ϊ6λ���ϣ��밴Ҫ����������");
					newpasswordField.setText("");
					return;
				} else {
					if (num == 1) {
						jLabel.setText("����ǿ�ȣ���");

					}

					if (num == 2) {
						jLabel.setText("����ǿ�ȣ���");

					}

					if (num == 3) {
						jLabel.setText("����ǿ�ȣ�ǿ");

					}

				}

				String e_account = account;

				String newpassword1 = newpasswordField.getText();
				RevisePassword_.this.dispose();

				RevisePassword_e revisePassword_e = new RevisePassword_e(
						e_account, newpassword1);

			}

		});

		// �˳���ť
		exitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				RevisePassword_.this.dispose();
				MainView_User mainView_User = new MainView_User(account + "");

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
