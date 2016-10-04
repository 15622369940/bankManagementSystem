package view_Counter;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import view_ATM.Desposit_Main;
import view_ATM.MainView_User;
import view_Admin.MainView_Admin;


public class Desposit_counter extends JDialog {

private Double amount = null;
	
	private JLabel tishi1Label = new JLabel("�������");
	private JLabel tishi2Label = new JLabel("��ѡ�������ͣ�");

	private JTextField jeField = new JTextField();

	private JRadioButton hqButton = new JRadioButton("����");
	private JRadioButton dqButton = new JRadioButton("����");

	private JButton giveupButton = new JButton("��������");
	private JButton yesButton = new JButton("ȷ�ϴ��");

	public Desposit_counter(final String account_admin, final String account) {

		this.setLayout(null); // ���ַ�ʽ�����Բ���
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

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
		yesButton.setBounds(380, 200, 90, 35);
		add(yesButton);

		//�������ݿ⣬��Ǯ������
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

			String sql = "select balance from currentAccount where userAccount = ?";
			PreparedStatement ps  = connection.prepareStatement(sql);
			ps.setObject(1, account);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				
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
				int n=0;
				if (hqButton.isSelected()) {
					int jine = Integer.parseInt(jeField.getText());
					
					try {
						//�����ݿ⣬����Ǯ
						Class.forName("com.mysql.jdbc.Driver");
						Connection connection = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

						String sql = "update currentAccount set balance = ? where userAccount = ?";
						PreparedStatement ps = connection.prepareStatement(sql);
						ps.setObject(1, amount+jine);
						ps.setObject(2, account);
						n = ps.executeUpdate();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(n>0){
						jeField.setText("");
						JOptionPane.showMessageDialog(null, "���ɹ���");
						JOptionPane.showMessageDialog(null, "����ǰ�˻������Ϊ��"+(amount+jine));
						Desposit_counter.this.dispose();
						MainView_Admin mainView_User = new MainView_Admin(account_admin);
					}else{
						jeField.setText("");
						JOptionPane.showMessageDialog(null, "���ʧ�ܣ�");
						return;
					}
							

				}
				if (dqButton.isSelected()) {
					System.out.println("����");
				}

			}

		});

		giveupButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Desposit_counter.this.dispose();
				MainView_Admin mainView_User = new MainView_Admin(account);

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
	
	
	
	
}
