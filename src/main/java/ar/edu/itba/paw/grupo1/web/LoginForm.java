package ar.edu.itba.paw.grupo1.web;

public class LoginForm {

	private String username;
	
	private String password;
	
	private boolean rememberName;
	
	private boolean rememberMe;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRememberName() {
		return rememberName;
	}

	public void setRememberName(boolean rememberName) {
		this.rememberName = rememberName;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	
}
