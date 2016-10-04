package view_Counter;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import view_ATM.MainView_User;
import view_ATM.Recharge_pay;
import view_ATM.Recharge_pay2;
import view_Admin.MainView_Admin;


public class Recharge_pay_counter extends JDialog {

	private JLabel etishiLabel = new JLabel(
			"Please select or enter a payment type");
	private JLabel ctishiLabel = new JLabel("��ѡ�������ɷ�����");

	private JButton b1 = new JButton("�ƶ�����");
	private JButton b2 = new JButton("���Ż���");
	private JButton b3 = new JButton("��ͨ����");
	private JButton b4 = new JButton("�籣�ɷ�");
	private JButton b5 = new JButton("���ϸ���");
	private JButton b6 = new JButton("�������");
	private JButton b7 = new JButton("�����ƿ�");
	private JButton b8 = new JButton("ˮ��");
	private JButton b9 = new JButton("ȼ����");

	private JButton quxiaoButton = new JButton("ȡ��");
	private JButton xyButton = new JButton("��һҳ");

	public Recharge_pay_counter(final String account) {

		this.setLayout(null); // ���ַ�ʽ�����Բ���
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		etishiLabel.setBounds(120, 20, 620, 55);
		add(etishiLabel);
		etishiLabel.setFont(new Font("", Font.PLAIN, 36));
		etishiLabel.setForeground(Color.black);

		ctishiLabel.setBounds(300, 70, 350, 55);
		ctishiLabel.setForeground(Color.black);
		add(ctishiLabel);
		ctishiLabel.setFont(new Font("�п�", Font.PLAIN, 26));

		quxiaoButton.setBounds(80, 460, 100, 40);
		add(quxiaoButton);
		xyButton.setBounds(680, 460, 100, 40);
		add(xyButton);

		b1.setBounds(143, 150, 145, 60);
		add(b1);
		b2.setBounds(360, 150, 145, 60);
		add(b2);
		b3.setBounds(580, 150, 145, 60);
		add(b3);

		b4.setBounds(143, 250, 145, 60);
		add(b4);
		b5.setBounds(360, 250, 145, 60);
		add(b5);
		b6.setBounds(580, 250, 145, 60);
		add(b6);

		b7.setBounds(143, 350, 145, 60);
		add(b7);
		b8.setBounds(360, 350, 145, 60);
		add(b8);
		b9.setBounds(580, 350, 145, 60);
		add(b9);

		b1.setForeground(Color.red);
		b2.setForeground(Color.red);
		b3.setForeground(Color.red);
		b4.setForeground(Color.red);
		b5.setForeground(Color.red);
		b6.setForeground(Color.red);
		b7.setForeground(Color.red);
		b8.setForeground(Color.red);
		b9.setForeground(Color.red);

		b1.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		b2.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		b3.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		b4.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		b5.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		b6.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		b7.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		b8.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		b9.setFont(new Font("΢���ź�", Font.PLAIN, 24));

		b1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					Runtime
							.getRuntime()
							.exec(
									"rundll32 url.dll,FileProtocolHandler http://www.10086.cn/index_5074.htm");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

		b2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					Runtime
							.getRuntime()
							.exec(
									"rundll32 url.dll,FileProtocolHandler http://www.chinaunicom.com.cn/");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		b3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					Runtime
							.getRuntime()
							.exec(
									"rundll32 url.dll,FileProtocolHandler http://189.cn/js/");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		b4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					Runtime
							.getRuntime()
							.exec(
									"rundll32 url.dll,FileProtocolHandler http://www.10086.cn/index_5074.htm");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		b5.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					Runtime
							.getRuntime()
							.exec(
									"rundll32 url.dll,FileProtocolHandler http://www.10086.cn/index_5074.htm");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		b6.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					Runtime
							.getRuntime()
							.exec(
									"rundll32 url.dll,FileProtocolHandler http://www.10086.cn/index_5074.htm");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		b7.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					Runtime
							.getRuntime()
							.exec(
									"rundll32 url.dll,FileProtocolHandler http://www.10086.cn/index_5074.htm");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		b8.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					Runtime
							.getRuntime()
							.exec(
									"rundll32 url.dll,FileProtocolHandler http://www.10086.cn/index_5074.htm");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		b9.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					Runtime
							.getRuntime()
							.exec(
									"rundll32 url.dll,FileProtocolHandler http://www.10086.cn/index_5074.htm");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

		quxiaoButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Recharge_pay_counter.this.dispose();
				MainView_Admin mainView_User = new MainView_Admin(account);

			}

		});
		xyButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Recharge_pay_counter.this.dispose();
				Recharge_pay2 recharge_pay2 = new Recharge_pay2(account);

			}

		});

		this.setTitle("��ֵ�ɷ�");
		this.setResizable(false); // �����϶������
		this.setSize(850, 600);
		this.setLocationRelativeTo(null); // ����
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // �رգ���ʲô������
		this.setVisible(true); // ������ӻ�
	}

}
