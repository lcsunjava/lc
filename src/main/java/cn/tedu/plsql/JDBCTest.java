package cn.tedu.plsql;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;

import oracle.jdbc.OracleTypes;

public class JDBCTest {
	private static final Properties config = new Properties();
	static{
		InputStream is = JDBCTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			config.load(is);
			//Class.forName(config.getProperty("jdbc.driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static Connection getCon() throws Exception{
		String url = config.getProperty("jdbc.url");
		String user = config.getProperty("jdbc.username");
		String password = config.getProperty("jdbc.password");
		System.out.println(user);
		return DriverManager.getConnection(url, user, password);
		
	}
	
	public static void main(String[] args) {
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			/*con = getCon();
			cs = con.prepareCall("{call getManager(?,?)}");
			cs.setString(1,"SMITH");
			cs.registerOutParameter(2, java.sql.Types.VARCHAR);
			cs.execute();
			System.out.println(cs.getString(2));*/
			
			con = getCon();
			cs = con.prepareCall("{call sys.fenye(?,?,?,?,?,?)}");
			cs.setString(1, "EMP");
			cs.setInt(2, 5);
			cs.setInt(3, 1);
			
			cs.registerOutParameter(4, java.sql.Types.INTEGER);
			cs.registerOutParameter(5, java.sql.Types.INTEGER);
			cs.registerOutParameter(6, OracleTypes.CURSOR);
			cs.execute();
			
			int rowscount = cs.getInt(4);
			int pagecount = cs.getInt(5);
			rs = (ResultSet)cs.getObject(6);
			System.out.println("总记录数:" + rowscount);
			System.out.println("总页数:" + pagecount);
			System.out.println("---------------");
			while(rs.next()) {
				System.out.println(rs.getString("ename") + ":" + rs.getString("rn"));
			}



		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
