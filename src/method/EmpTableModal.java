package method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
public class EmpTableModal {
	public static DefaultTableModel getEmpTableModal() {
		// ��������ñ�ģ�� -->���ñ�ͷ�ͱ�����
		Vector<String> thVector = new Vector<String>();
		thVector.add("Ա�����");
		thVector.add("Ա������");
		thVector.add("�Ա�");
		thVector.add("����");
		thVector.add("Ա������");
		thVector.add("��ְ����");
		thVector.add("��������");
		// ������
		Vector<Vector<String>> dateVector = new Vector<Vector<String>>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/Bank_Management_System", "root",
					"admin");
			String sql = "select empnum,depnum,empborn,hireday,userAccount from emp";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) { 
				Vector<String> vector = new Vector<String>();
				String hireday = rs.getString(4);
				//1Ա�����
				vector.add(rs.getInt(1) + "");
				Date borndate = rs.getDate(3);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(borndate);
				int n = calendar.get(Calendar.YEAR);
				int s2 = rs.getInt(2);
				java.util.Date now = new java.util.Date();
				calendar.setTime(now);
				int n1 = calendar.get(Calendar.YEAR);
				int age;
				age = n1 - n;
				String userAccount = rs.getString(5);
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection5 = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1/Bank_Management_System",
						"root", "admin");
				String sql5 = "select userName,userSex,userTel from userinfo where userAccount = ?";
				PreparedStatement ps5 = connection5.prepareStatement(sql5);
				ps5.setObject(1, userAccount);
				ResultSet rs5 = ps5.executeQuery();
				while (rs5.next()) {
					vector.add(rs5.getString(1));
					if (rs5.getInt(2) == 1)
						vector.add("��");
					else{
					vector.add("Ů");
					}
					//2����
					vector.add(age + "");
					vector.add(rs5.getString(3));
					vector.add(hireday);
				}
				Connection connection2 = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1/Bank_Management_System",
						"root", "admin");
				String sql2 = "select depname from dep where depnum = ?";
				PreparedStatement ps2 = connection2.prepareStatement(sql2);
				ps2.setObject(1, s2);
				ResultSet rs2 = ps2.executeQuery();
				rs2.next();
				vector.add(rs2.getString(1));
				dateVector.add(vector);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DefaultTableModel defaultTableModel = new DefaultTableModel(dateVector,
				thVector);
		return defaultTableModel;
	}
}