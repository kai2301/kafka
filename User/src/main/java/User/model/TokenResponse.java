package User.model;

import java.util.List;

public class TokenResponse {
	private String access_token;
	private LoginResponse user_info;
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public LoginResponse getUser_info() {
		return user_info;
	}
	public void setUser_info(LoginResponse user_info) {
		this.user_info = user_info;
	}
	public TokenResponse(String access_token, LoginResponse user_info) {
		super();
		this.access_token = access_token;
		this.user_info = user_info;
	}
	public TokenResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
