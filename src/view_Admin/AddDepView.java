package view_Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import method.DepTableModal;

public class AddDepView extends JDialog {

	private JLabel tishiLabel = new JLabel("��ǰ���в��ţ�");
	private JLabel depnumLabel = new JLabel("���ű��");
	private JLabel depnameLabel = new JLabel("��������");
	private JLabel depmanagerLabel = new JLabel("��������");
	private JLabel deptelLabel = new JLabel("���ŵ绰");

	private JTextField depnumField = new JTextField();
	private JTextField depnameField = new JTextField();
	private JTextField depmanagerField = new JTextField();
	private JTextField deptelField = new JTextField();

	private JButton adddepButton = new JButton("���");
	private JButton clearButton = new JButton("���");

	private JTable table = new JTable() {

		// ��д����
		public boolean isCellEditable(int row, int column) { // �к���
			return false;
		}
	};
	private JScrollPane pane = new JScrollPane();

	public AddDepView() {
		this.setTitle("��Ӳ���");
		this.setSize(600, 460);
		this.setLocationRelativeTo(null);
		this.setLayout(null);

		// �÷���ʵ�֣�д���ı�ģ��
		DefaultTableModel defaultTableModel = DepTableModal.getDepTableModal();
		table.setModel(defaultTableModel);

		pane.setBounds(20, 60, 450, 160);
		pane.getViewport().add(table);
		add(pane);

		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);

		// ��Ⱦ��
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, cellRenderer);

		tishiLabel.setBounds(90, 30, 160, 20);
		add(tishiLabel);

		depnumLabel.setBounds(46, 250, 69, 25);
		depnumField.setBounds(115, 250, 85, 25);
		add(depnumLabel);
		add(depnumField);

		depnameLabel.setBounds(225, 250, 69, 25);
		depnameField.setBounds(300, 250, 120, 25);
		add(depnameLabel);
		add(depnameField);

		depmanagerLabel.setBounds(46, 300, 69, 25);
		depmanagerField.setBounds(115, 300, 85, 25);
		add(depmanagerLabel);
		add(depmanagerField);

		deptelLabel.setBounds(225, 300, 69, 25);
		deptelField.setBounds(300, 300, 120, 25);
		add(deptelLabel);
		add(deptelField);

		adddepButton.setBounds(260, 350, 69, 30);
		add(adddepButton);

		clearButton.setBounds(370, 350, 69, 30);
		add(clearButton);

		adddepButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String depnum = depnumField.getText();
				String depname = depnameField.getText();
				String depmanager = depmanagerField.getText();
				String deptel = deptelField.getText();
				if ("".equals(depnum) || depnum == null) {
					JOptionPane.showMessageDialog(null, "���ű�Ų���Ϊ�գ�\n\t"
							+ "�����²�����");
					depnumField.setText("");
					depnameField.setText("");
					depmanagerField.setText("");
					deptelField.setText("");
					return;
				}
				if ("".equals(depname) || depname == null) {
					JOptionPane.showMessageDialog(null, "�������Ʋ���Ϊ�գ�\n\t"
							+ "�����²�����");
					depnumField.setText("");
					depnameField.setText("");
					depmanagerField.setText("");
					deptelField.setText("");
					return;
				}
				if ("".equals(depmanager) || depmanager == null) {
					JOptionPane.showMessageDialog(null, "�������ܲ���Ϊ�գ�\n\t"
							+ "�����²�����");
					depnumField.setText("");
					depnameField.setText("");
					depmanagerField.setText("");
					deptelField.setText("");
					return;
				}
				if ("".equals(depnum) || depnum == null) {
					JOptionPane.showMessageDialog(null, "���ŵ绰����Ϊ�գ�\n\t"
							+ "�����²�����");
					depnumField.setText("");
					depnameField.setText("");
					depmanagerField.setText("");
					deptelField.setText("");
					return;
				}

				// JDBC����
				// ����ò����Ƿ��Ѿ����� ��Ϊ���������Բ����ظ���һ���ظ��ͻᱨ��
				String sql;
				try {
					List list = new ArrayList();
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection9 = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1/Bank_Management_System",
							"root", "admin");
					sql = "select depnum from dep";
					ResultSet rs5 = null;
					PreparedStatement ps = connection9.prepareStatement(sql);
					rs5 = ps.executeQuery();
					int i = 0;
					String[] s = new String[100];
					while (rs5.next()) {
						s[i] = rs5.getString(1).toString();
						i++;
					}
					for (int j = 0; j < s.length; j++) {
						if(depnum.equals(s[j])){
							JOptionPane.showMessageDialog(null, "�ò��ű���Ѿ����ڣ����������룡");
							depnumField.setText("");
							return;
						}
					}
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				int n = 0;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1/Bank_Management_System",
							"root", "admin");

					String sql4 = "insert into dep(depnum,depname,depmanager,deptel) values(?,?,?,?)";

					PreparedStatement ps4 = connection.prepareStatement(sql4);
					ps4.setObject(1, depnum);
					ps4.setObject(2, depname);
					ps4.setObject(3, depmanager);
					ps4.setObject(4, deptel);

					n = ps4.executeUpdate();
					if (n == 0) {
						JOptionPane.showMessageDialog(null, "���ʧ��");
					} else {
						JOptionPane.showMessageDialog(null, "��ӳɹ�");
						table.setModel(DepTableModal.getDepTableModal());
						depnumField.setText("");
						depnameField.setText("");
						depmanagerField.setText("");
						deptelField.setText("");
					}

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

		clearButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				depnumField.setText("");
				depnameField.setText("");
				depmanagerField.setText("");
				deptelField.setText("");

			}

		});

		this.setModal(true);
		this.setVisible(true);

	}

	public static void main(String[] args) {
		AddDepView addDepView = new AddDepView();
	}

}
