package pojo;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DbzTableModal {

	public static DefaultTableModel getDbzTableModal(String account_) {

		// �������ñ�ģ�� -->���ñ�ͷ�ͱ�����

		Vector<String> thVector = new Vector<String>();
		thVector.add("����");
		thVector.add("�˻����");
		thVector.add("�������");

		// ������
		Vector<Vector<String>> dateVector = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");
			String sql = "select Amount,aAmount,moneyType from moneyTypes where userAccount = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setObject(1, account_);
			ResultSet rs = ps.executeQuery();

			dateVector = new Vector<Vector<String>>();

			while (rs.next()) {

				Vector<String> vector = new Vector<String>();



				int type = rs.getInt(3);
				
				if (type == 1) {
					vector.add("Ӣ��");
				} else if (type == 2) {
					vector.add("��Ԫ");
				} else if (type == 3) {
					vector.add("�����");
				} else if (type == 4) {
					vector.add("�۱�");
				} else {
					JOptionPane.showMessageDialog(null, "��֧�ֵĻ�������");
				}

				vector.add(rs.getDouble(1) + "");
				vector.add(rs.getObject(2) + "");
				
				dateVector.add(vector);
				
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DefaultTableModel defaultTableModel = new DefaultTableModel(dateVector,thVector);
		return defaultTableModel;

	}

}
