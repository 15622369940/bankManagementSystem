package util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

public class DbUtil {

	// �������ӳ�
	public static Vector<Connection> connectionpool = new Vector<Connection>();

	// ��̬��
	static {
		Properties properties = new Properties();
		try {
			properties.load(DbUtil.class.getResourceAsStream("/db.properties"));
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			String driver = properties.getProperty("driver");

			Class.forName(driver);
			for (int i = 0; i < 10; i++) {
				Connection connection = DriverManager.getConnection(url,
						username, password);
				connectionpool.add(connection);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ȡ����
	public static Connection getConnection() {
		Connection connection = connectionpool.get(0);
		connectionpool.remove(0);
		return connection;
	}

	// �ͷ�����
	public static void releaseconnection(Connection connection) {
		connectionpool.add(connection);
	}

	// ��ɾ��
	public static int zsg(String sql, Object... p) {
		Connection connection = getConnection();

		int n = 0;
		//
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			// ��p�����ֵ��ֵ������
			if (p != null) {
				for (int i = 0; i < p.length; i++) {
					// �±��1��ʼ
					ps.setObject(i + 1, p[i]);
				}
			}
			// ִ��sql���
			n = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			releaseconnection(connection);
		}

		return n;
	}

	// ��ѯ
	public static List query(Class c, String sql, Object... p) {
		List list = new ArrayList();
		Connection connection = getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			if (p != null) {
				for (int i = 0; i < p.length; i++) {
					ps.setObject(i + 1, p[i]);
				}
			}
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			// ����ֶ�����
			int n = rsmd.getColumnCount();
			while (rs.next()) {
				Object object = c.newInstance();
				for (int i = 1; i <= n; i++) {
					String fieldname = rsmd.getColumnName(i);
					Object value = rs.getObject(fieldname);
					Field field = c.getDeclaredField(fieldname);
					field.setAccessible(true); // ���óɿ��Ը���
					field.set(object, value);
				}
				list.add(object);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			releaseconnection(connection);
		}
		return list;
	}

	// �ۼ���ѯ
	public static int uniqueQuery(String sql, Object... p) {
		int n = 0;
		Connection connection = getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			if (p != null) {
				for (int i = 0; i < p.length; i++) {
					ps.setObject(i + 1, p[i]);
				}
			}
			ResultSet rs = ps.executeQuery();
			// �ѽ�������Ƶ��н�����У���������ͷ��
			rs.next();
			n = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			releaseconnection(connection);
		}

		return n;
	}

}
