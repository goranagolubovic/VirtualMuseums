package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import beans.UserAccountBean;

public class UserAccountService {
	static String driver = "com.mysql.jdbc.Driver";
	static String connectionUrl = "jdbc:mysql://localhost:3306/museum?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
	static String userid = "root";
	static String password = "student";
	static Connection connection = null;
	static Statement statement = null;
	static ResultSet resultSet = null;
	public UserAccountService() {
		// TODO Auto-generated constructor stub
	}
	public static void deleteUser(String id) { 
		try
		{
		Class.forName(driver);
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/museum?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "root", "student");
		Statement st=conn.createStatement();
		int i=st.executeUpdate("DELETE FROM korisnik WHERE id="+id);
		}
		catch(Exception e)
		{
		System.out.print(e);
		e.printStackTrace();
		}
	   } 
	public static  List<UserAccountBean>getUsers(){
		List<UserAccountBean> list=new ArrayList<>();
		try{
			Class.forName(driver);
			connection = DriverManager.getConnection(connectionUrl, userid, password);
			statement=connection.createStatement();
			String sql ="select * from korisnik where is_admin=false";
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				UserAccountBean userBean=new UserAccountBean(resultSet.getInt("id"),resultSet.getString("ime")
						,resultSet.getString("prezime"),resultSet.getString("korisnicko_ime"),
						resultSet.getString("lozinka"),resultSet.getString("email"),resultSet.getBoolean("is_admin"),resultSet.getBoolean("is_active"),resultSet.getBoolean("is_approved")
						);
				list.add(userBean);
			}
			
	}catch(Exception ex) {
		
	}
		return list;
	}
	private static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
	public static void updatePassword(String id) {
		String newPassword=getSaltString();
		try
		{
		Class.forName(driver);
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/museum?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "root", "student");
		Statement st=conn.createStatement();
		int i=st.executeUpdate("UPDATE korisnik SET lozinka='"+newPassword+"' WHERE id="+id);
		}
		catch(Exception e)
		{
		System.out.print(e);
		e.printStackTrace();
		}
	}
	public static void updateApprovement(String id,String approver) {
		int approved=0;
		if("on".equals(approver)) {
			approved=1;
		}
		try
		{
		Class.forName(driver);
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/museum?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "root", "student");
		Statement st=conn.createStatement();
		int i=st.executeUpdate("UPDATE korisnik SET is_approved="+approved+" WHERE id="+id);
		}
		catch(Exception e)
		{
		System.out.print(e);
		e.printStackTrace();
		}
	}
}
