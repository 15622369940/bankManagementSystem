package view_ATM;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FirstView_User extends JFrame {

	private JLabel mesLabel = new JLabel("��ѡ����Ҫ���еĲ�����");

	private JButton loginButton = new JButton("�û���½");
	private JButton specialDrawButton = new JButton("��Լȡ��");

	public FirstView_User() {

		this.setTitle("��ӭ�������й���ϵͳ");
		this.setSize(360, 200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLayout(null);

		mesLabel.setBounds(90, 20, 190, 30);
		loginButton.setBounds(110, 58, 140, 30);
		specialDrawButton.setBounds(110, 110, 140, 30);

		mesLabel.setFont(new Font("", Font.PLAIN, 16));
		loginButton.setFont(new Font("", Font.PLAIN, 14));
		specialDrawButton.setFont(new Font("", Font.PLAIN, 14));

		ButtonGroup group = new ButtonGroup();
		group.add(loginButton);
		group.add(specialDrawButton);

		loginButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				FirstView_User.this.dispose();
				LoginView_User loginView_User = new LoginView_User();

			}

		});

		specialDrawButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				FirstView_User.this.dispose();
				SpecialDraw specialDraw = new SpecialDraw();

			}

		});

		add(mesLabel);
		add(loginButton);
		add(specialDrawButton);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "ȷ���˳���", "��ʾ",
						JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					System.exit(0);
				} else {
					return;
				}
			}
		});
		this.setVisible(true);

	}

	public static void main(String[] args) {
		FirstView_User firstView_User = new FirstView_User();
	}
}
