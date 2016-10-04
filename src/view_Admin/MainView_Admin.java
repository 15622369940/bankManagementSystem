package view_Admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import view_ATM.MainView_User.Shijian;
import view_Counter.Draw_main_counter;
import view_Counter.GetAccount_Desposit;
import view_Counter.GetAccount_Draw;
import view_Counter.GetAccount_Inquiry;
import view_Counter.Recharge_pay_counter;
import view_Counter.Register_counter;
import view_Counter.Revise_First_counter;

public class MainView_Admin extends JFrame implements Runnable {

	JMenuBar bar = new JMenuBar();

	JMenu depMenu = new JMenu("���Ź���");
	JMenuItem getdepMenu = new JMenuItem("�鿴����");
	JMenuItem adddepMenu = new JMenuItem("��Ӳ���");
	JMenuItem updatedepMenu = new JMenuItem("�޸���Ϣ");
	JMenuItem deldepMenu = new JMenuItem("ɾ������");

	JMenu empMenu = new JMenu("Ա������");
	JMenuItem getempMenu = new JMenuItem("�鿴Ա��");
	JMenuItem addempMenu = new JMenuItem("���Ա��");
	JMenuItem updateempMenu = new JMenuItem("�޸���Ϣ");
	JMenuItem delempMenu = new JMenuItem("ɾ��Ա��");

	private JLabel timeLabel = new JLabel("��ǰϵͳʱ��Ϊ��");
	private JLabel time1Label = new JLabel();

	private JLabel etishiLabel = new JLabel("Please    Select    Service");
	private JLabel ctishiLabel = new JLabel("��ѡ������ҵ��");

	private JButton zhuanzhangButton = new JButton("ת��ҵ��");
	private JButton qukuanButton = new JButton("ȡ��");
	private JButton chongzhiButton = new JButton("��ֵ�ɷ�");
	private JButton cunkuanButton = new JButton("ʵʱ���");
	private JButton licaiButton = new JButton("���û�ע��");
	private JButton xiugaimimaButton = new JButton("�޸��û���Ϣ");
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

	public MainView_Admin(final String account_admin) {
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

		etishiLabel.setBounds(190, 40, 500, 55);
		add(etishiLabel);
		etishiLabel.setFont(new Font("", Font.PLAIN, 43));
		etishiLabel.setForeground(Color.red);

		ctishiLabel.setBounds(320, 90, 350, 55);
		ctishiLabel.setForeground(Color.red);
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

		depMenu.add(getdepMenu);
		depMenu.add(adddepMenu);
		depMenu.add(updatedepMenu);
		depMenu.add(deldepMenu);

		empMenu.add(getempMenu);
		empMenu.add(addempMenu);
		empMenu.add(updateempMenu);
		empMenu.add(delempMenu);

		bar.add(depMenu);
		bar.add(empMenu);

		bar.setBounds(0, 0, 850, 20);
		add(bar);

		depMenu.setEnabled(false);
		empMenu.setEnabled(false);

		if (account_admin.equals("6217002940103996053")) {
			depMenu.setEnabled(true);
			empMenu.setEnabled(true);
		}

		JLabel lbl_image = new JLabel();
		lbl_image.setBounds(0, 0, 850, 600);
		lbl_image.setIcon(new ImageIcon("src/Image/����Ա���汳��.jpg"));
		add(lbl_image);
		// �޸��û���Ϣ
		xiugaimimaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView_Admin.this.dispose();
				Revise_First_counter revise_First_counter = new Revise_First_counter(
						account_admin);
			}
		});
		// ���û�ע��
		licaiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView_Admin.this.dispose();
				JOptionPane.showMessageDialog(null,
						"ע�����û���Ҫ����50�����������Ѽӿ��ѹ�20��ʣ������뿨�ڣ�");
				Register_counter register_counter = new Register_counter(
						account_admin);
			}
		});
		// ת��
		zhuanzhangButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ������һ�����棬�����˺����룬�õ�������Ϣ����ת���ʼ��ת�˽���
				MainView_Admin.this.dispose();
				GetAccount_main getAccount_main = new GetAccount_main(
						account_admin);
			}
		});
		// ȡ��
		qukuanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView_Admin.this.dispose();
				GetAccount_Draw getAccount_main = new GetAccount_Draw(
						account_admin);
			}
		});
		// ��ֵ�ɷ�
		chongzhiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView_Admin.this.dispose();
				Recharge_pay_counter recharge_pay_counter = new Recharge_pay_counter(
						account_admin);
			}
		});
		// ���
		cunkuanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView_Admin.this.dispose();
				GetAccount_Desposit getAccount_main = new GetAccount_Desposit(
						account_admin);
			}
		});
		// ��ѯҵ��
		chaxunyewuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView_Admin.this.dispose();
				GetAccount_Inquiry getAccount_main = new GetAccount_Inquiry(
						account_admin);
			}
		});
		// �˳�
		tuichuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "ȷ���˳���", "��ʾ",
						JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					JOptionPane.showMessageDialog(null, "�˳��ɹ���ף������˳����");
					System.exit(0);
				} else {
					return;
				}
			}
		});
		// �鿴����
		getdepMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowAllDep showAllDep = new ShowAllDep();
			}
		});
		// ��Ӳ���
		adddepMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddDepView addDep = new AddDepView();
			}
		});
		// �޸Ĳ���
		updatedepMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateDepView updateDepView = new UpdateDepView();
			}
		});
		// ɾ������
		deldepMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DelDepView delDepView = new DelDepView();
			}
		});
		// �鿴Ա��
		getempMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowAllEmp showAllEmp = new ShowAllEmp(account_admin);
			}
		});
		// ���Ա��
		addempMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddEmpView addEmpView = new AddEmpView();
			}
		});
		// �޸���Ϣ
		updateempMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateEmpView updateEmpView = new UpdateEmpView();
			}
		});
		// ɾ����Ϣ
		delempMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DelEmpView delEmpView = new DelEmpView();
			}
		});
		// ��Ӽ����¼����رմ���
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "ȷ���˳���", "��ʾ",
						JOptionPane.YES_NO_OPTION);

				if (n == 0) {
					System.exit(0);
				} else {
					// �����������
					return;
				}
			}

		});
		this.setTitle("������");
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

		MainView_Admin mainView_Admin = new MainView_Admin("613258200");
		Thread thread = new Thread(mainView_Admin);
		thread.start();
	}

}
