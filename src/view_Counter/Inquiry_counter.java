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
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import view_ATM.Inquiry_main;
import view_ATM.MainView_User;
import view_ATM.MoneyTypes_Main;
import view_Admin.MainView_Admin;


public class Inquiry_counter extends JDialog {
	
	private JLabel etishiLabel = new JLabel("Please    Select    Service");
	private JLabel ctishiLabel = new JLabel("��ѡ������ҵ��");
	
	private JLabel yeLabel = new JLabel("�����˻����Ϊ��");
	private JLabel eyeLabel = new JLabel("Account Balance");
	
	private JLabel ayeLabel = new JLabel("�������Ϊ��");
	private JLabel aeyeLabel = new JLabel("Available Balance");
	
	private JTextField yeField = new JTextField();
	private JTextField ayeField = new JTextField();
	
	private JButton duobizhongButton = new JButton("��������");
	private JButton tuichuButton = new JButton("�˳�");
	private JButton chaxunjiluButton = new JButton("��ѯ��¼");
	private JButton fanhuiButton = new JButton("�������˵�");
	
	final DecimalFormat df = new DecimalFormat("0.00");
	
	public Inquiry_counter(final String account_admin, final String account_) {
		
		this.setLayout(null); // ���ַ�ʽ�����Բ���
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
        duobizhongButton.setBounds(90, 310, 110, 40);
        add(duobizhongButton);
        tuichuButton.setBounds(90, 370, 110, 40);
        add(tuichuButton);

        chaxunjiluButton.setBounds(480, 310, 110, 40);
        add(chaxunjiluButton);
        fanhuiButton.setBounds(480, 370, 110, 40);
        add(fanhuiButton);
        

		yeLabel.setBounds(170, 120, 170, 40);
		add(yeLabel);
		yeLabel.setFont(new Font("",Font.PLAIN,20));
		
		eyeLabel.setBounds(180, 145, 120, 40);
		add(eyeLabel);
		eyeLabel.setFont(new Font("",Font.PLAIN,16));
		
		yeField.setBounds(350, 130, 140, 40);
		add(yeField);
		yeField.setFont(new Font("",Font.PLAIN,20));
		
		ayeLabel.setBounds(170, 200, 170, 40);
		add(ayeLabel);
		ayeLabel.setFont(new Font("",Font.PLAIN,20));
		
		aeyeLabel.setBounds(180, 230, 130, 40);
		add(aeyeLabel);
		aeyeLabel.setFont(new Font("",Font.PLAIN,16));
		
		ayeField.setBounds(350, 210, 140, 40);
		add(ayeField);
		ayeField.setFont(new Font("",Font.PLAIN,20));
		
		etishiLabel.setBounds(190, 20, 500, 45);
		add(etishiLabel);
		etishiLabel.setFont(new Font("", Font.PLAIN, 33));
		etishiLabel.setForeground(Color.black);

		ctishiLabel.setBounds(300, 60, 350, 40);
		ctishiLabel.setForeground(Color.black);
		add(ctishiLabel);
		ctishiLabel.setFont(new Font("�п�", Font.PLAIN, 22));
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");

			String sql = "select balance from currentAccount where userAccount=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setObject(1, account_);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				
				yeField.setText(df.format(rs.getDouble(1)));
				ayeField.setText(df.format(rs.getDouble(1)));
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		duobizhongButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				Inquiry_counter.this.dispose();
				MoneyTypes_Main_counter moneyTypes_Main = new MoneyTypes_Main_counter(account_admin,account_);
				
			}
			
			
		});
		
		
		tuichuButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				

				int n = JOptionPane.showConfirmDialog(null, "ȷ���˳���","��ʾ",JOptionPane.YES_NO_OPTION);
				
				if(n==0){
					JOptionPane.showMessageDialog(null, "�˳��ɹ���ף������˳����");
					System.exit(0);
				}else{
					return;
				}
				
			}
			
			
			
		});
		
		fanhuiButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				Inquiry_counter.this.dispose();
				MainView_Admin mainView_User = new MainView_Admin(account_);
				
				
			}
			
			
			
		});
		
		this.setTitle("��ѯҵ��");
		this.setResizable(false); // �����϶������
		this.setSize(700, 500);
		this.setLocationRelativeTo(null); // ����
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // �رգ���ʲô������
		this.setVisible(true); // ������ӻ�
	}

}