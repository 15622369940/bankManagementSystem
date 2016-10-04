package view_ATM;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MainView_User extends JFrame implements Runnable {

	private JLabel timeLabel = new JLabel("��ǰϵͳʱ��Ϊ��");
	private JLabel time1Label = new JLabel();

	private JLabel etishiLabel = new JLabel("Please    Select    Service");
	private JLabel ctishiLabel = new JLabel("��ѡ������ҵ��");

	private JButton zhuanzhangButton = new JButton("ת��ҵ��");
	private JButton qukuanButton = new JButton("ȡ��");
	private JButton chongzhiButton = new JButton("��ֵ�ɷ�");
	private JButton cunkuanButton = new JButton("ʵʱ���");
	private JButton licaiButton = new JButton("���Ƶ��");
	private JButton xiugaimimaButton = new JButton("�޸�����");
	private JButton tuichuButton = new JButton("�˳�");
	private JButton chaxunyewuButton = new JButton("��ѯҵ��");

	public class Shijian implements Runnable {

		public void run() {
			// �̣߳���ʾ��ǰʱ��
			while (true) {
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String time = sdf.format(date);
				time1Label.setForeground(Color.red);
				time1Label.setText(time);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}

	public MainView_User(final String account) {

		// http://localhost:8080/Bank_Management_System_

		this.setLayout(null); // ���ַ�ʽ�����Բ���
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		Shijian shijian = new Shijian();
		Thread thread = new Thread(shijian);
		thread.start();

		timeLabel.setBounds(530, 500, 120, 20);
		timeLabel.setForeground(Color.red);
		add(timeLabel);
		time1Label.setBounds(680, 500, 120, 20);
		add(time1Label);

		etishiLabel.setBounds(190, 30, 500, 55);
		add(etishiLabel);
		etishiLabel.setFont(new Font("", Font.PLAIN, 43));
		etishiLabel.setForeground(Color.white);

		ctishiLabel.setBounds(320, 90, 350, 55);
		ctishiLabel.setForeground(Color.white);
		add(ctishiLabel);
		ctishiLabel.setFont(new Font("�п�", Font.PLAIN, 26));

		zhuanzhangButton.setBounds(130, 160, 180, 50);
		add(zhuanzhangButton);
		chongzhiButton.setBounds(130, 240, 180, 50);
		add(chongzhiButton);
		licaiButton.setBounds(130, 320, 180, 50);
		add(licaiButton);
		tuichuButton.setBounds(130, 400, 180, 50);
		add(tuichuButton);

		zhuanzhangButton.setForeground(Color.red);
		chongzhiButton.setForeground(Color.red);
		licaiButton.setForeground(Color.red);
		tuichuButton.setForeground(Color.red);

		zhuanzhangButton.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		chongzhiButton.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		licaiButton.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		tuichuButton.setFont(new Font("΢���ź�", Font.PLAIN, 24));

		qukuanButton.setBounds(560, 160, 180, 50);
		add(qukuanButton);
		cunkuanButton.setBounds(560, 240, 180, 50);
		add(cunkuanButton);
		xiugaimimaButton.setBounds(560, 320, 180, 50);
		add(xiugaimimaButton);
		chaxunyewuButton.setBounds(560, 400, 180, 50);
		add(chaxunyewuButton);

		qukuanButton.setForeground(Color.red);
		cunkuanButton.setForeground(Color.red);
		xiugaimimaButton.setForeground(Color.red);
		chaxunyewuButton.setForeground(Color.red);

		qukuanButton.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		cunkuanButton.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		xiugaimimaButton.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		chaxunyewuButton.setFont(new Font("΢���ź�", Font.PLAIN, 24));
		JLabel lbl_image = new JLabel();
		lbl_image.setBounds(0, 0, 850, 600);
		lbl_image.setIcon(new ImageIcon("src/Image/��ɫ����ͼ.jpg"));
		add(lbl_image);
		// ȡ��
		qukuanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String account_ = account;
				MainView_User.this.dispose();
				Draw_main draw_main = new Draw_main(account);

			}
		});
		// ���
		cunkuanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView_User.this.dispose();
				Desposit_Main desposit_Main = new Desposit_Main(account);
			}
		});
		zhuanzhangButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView_User.this.dispose();
				Transfer_accounts_Main transfer_accounts_Main = new Transfer_accounts_Main(
						account);
			}
		});
		xiugaimimaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accountnum = account;
				MainView_User.this.dispose();
				RevisePassword revisePassword = new RevisePassword(accountnum);
			}
		});
		tuichuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "ȷ���˳���", "��ʾ",
						JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					JOptionPane.showMessageDialog(null, "�˳��ɹ���ף������˳����������죡");
					System.exit(0);
				} else {
					return;
				}
			}
		});
		licaiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime
							.getRuntime()
							.exec(
									"rundll32 url.dll,FileProtocolHandler http://localhost:8080/Bank_Management_System_");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		chongzhiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView_User.this.dispose();
				Recharge_pay recharge_pay = new Recharge_pay(account);
			}
		});
		chaxunyewuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView_User.this.dispose();
				Inquiry_main inquiry_main = new Inquiry_main(account);
			}
		});
		this.setTitle("���˵�");
		this.setResizable(false); // �����϶������
		this.setSize(850, 600);
		this.setLocationRelativeTo(null); // ����
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // �رգ���ʲô������
		this.setVisible(true); // ������ӻ�
	}

	public void run() {

		// �̣߳���ʾ��ǰʱ��
		while (true) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(date);
			time1Label.setForeground(Color.red);
			time1Label.setText(time);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		MainView_User mainView_User = new MainView_User("350964661");
		Thread thread = new Thread(mainView_User);
		thread.start();
	}
}
