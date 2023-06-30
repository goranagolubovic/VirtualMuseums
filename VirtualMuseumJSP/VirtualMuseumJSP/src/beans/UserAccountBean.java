package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UserAccountBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5999259509194624596L;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getKorisnicko_ime() {
		return korisnicko_ime;
	}
	public void setKorisnicko_ime(String korisnicko_ime) {
		this.korisnicko_ime = korisnicko_ime;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getIs_admin() {
		return is_admin;
	}
	public void setIs_admin(Boolean is_admin) {
		this.is_admin = is_admin;
	}
	public Boolean getIs_active() {
		return is_active;
	}
	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}
	public Boolean getIs_approved() {
		return is_approved;
	}
	public void setIs_approved(Boolean is_approved) {
		this.is_approved = is_approved;
	}
	private int id;
	public UserAccountBean(int id, String ime, String prezime, String korisnicko_ime, String lozinka, String email,
			Boolean is_admin, Boolean is_active, Boolean is_approved) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnicko_ime = korisnicko_ime;
		this.lozinka = lozinka;
		this.email = email;
		this.is_admin = is_admin;
		this.is_active = is_active;
		this.is_approved = is_approved;
	}
	private String ime;
	private String prezime;
	private String korisnicko_ime;
	private String lozinka;
	private String email;
	private Boolean is_admin;
	private Boolean is_active;
	private Boolean is_approved;
	public UserAccountBean() {
		// TODO Auto-generated constructor stub
	}
	public void deleteUser(String id) {
				try
				{
				Class.forName("com.mysql.jdbc.Driver");
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

}
