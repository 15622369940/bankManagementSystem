package method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
public class DepTableModal {
	public static DefaultTableModel getDepTableModal() {
		// ��������ñ�ģ�� -->���ñ�ͷ�ͱ�����
		Vector<String> thVector = new Vector<String>();
		thVector.add("���ű��");
		thVector.add("��������");
		thVector.add("���ź���");
		thVector.add("��������");
		// ������
		Vector<Vector<String>> dateVector = new Vector<Vector<String>>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/Bank_Management_System", "root", "admin");
			String sql = "select depnum,depname,deptel,depmanager from dep";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()){
				
				Vector<String> vector = new Vector<String>();
				
				vector.add(rs.getInt(1) + "");
				vector.add(rs.getString(2));
				vector.add(rs.getString(3));
				vector.add(rs.getString(4));
				dateVector.add(vector);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		DefaultTableModel defaultTableModel = new DefaultTableModel(dateVector,thVector);
		return defaultTableModel;
	}
}
