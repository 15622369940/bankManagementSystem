package view_ATM;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import pojo.DbzTableModal;

import method.EmpTableModal;


public class MoneyTypes_Main extends JDialog {

	private JLabel etishiLabel = new JLabel("Transaction  Detail");
	private JLabel ctishiLabel = new JLabel("�������ϸ��Ϣ");
	
	private JButton tuichuButton = new JButton("�˳�");
	private JButton jixuButton = new JButton("��������");

	private JScrollPane pane = new JScrollPane();
	private JTable table = new JTable() {

		// ��д����
		public boolean isCellEditable(int row, int column) { // �к���
			return false;
		}

	};

	public MoneyTypes_Main(final String account) {
		this.setLayout(null); // ���ַ�ʽ�����Բ���
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		tuichuButton.setBounds(60, 360, 100, 45);
		add(tuichuButton);
		jixuButton.setBounds(480, 360, 100, 45);
		add(jixuButton);
		
		pane.setBounds(160, 120, 340, 200);

		table.setModel(DbzTableModal.getDbzTableModal(account));

		pane.getViewport().add(table);
		
		//�����еĿ��
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		table.getColumnModel().getColumn(1).setMaxWidth(140);
		table.getColumnModel().getColumn(2).setMaxWidth(140);
		
		add(pane);

		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
	    table.setDefaultRenderer(Object.class, cellRenderer);
		
		etishiLabel.setBounds(190, 20, 500, 45);
		add(etishiLabel);
		etishiLabel.setFont(new Font("", Font.PLAIN, 33));
		etishiLabel.setForeground(Color.black);

		ctishiLabel.setBounds(260, 60, 350, 40);
		ctishiLabel.setForeground(Color.black);
		add(ctishiLabel);
		ctishiLabel.setFont(new Font("�п�", Font.PLAIN, 22));

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
		
		jixuButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				MoneyTypes_Main.this.dispose();
				MainView_User mainView_User = new MainView_User(account);
				
			}
			
			
			
		});
		
		this.setTitle("��ѯҵ��");
		this.setResizable(false); // �����϶������
		this.setSize(660, 470);
		this.setLocationRelativeTo(null); // ����
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // �رգ���ʲô������
		this.setVisible(true); // ������ӻ�
	}

	public static void main(String[] args) {
		MoneyTypes_Main moneyTypes_Main = new MoneyTypes_Main("350964661");
	}
}
