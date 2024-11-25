package com.pcwk.ehr.user;

public class UserVO {
	//세로편집: alt + shift+ a
	//소문자: ctrl + shift + y
	//대문자: ctrl + shift + x
	private String userid	; //사용자ID
	private String name		; //이름
	private String password	; //비밀번호
	private String regdt	; //등록일
	
	public UserVO() {

	}
	
	public UserVO(String userid, String name, String password, String regdt) {
		super();
		this.userid = userid;
		this.name = name;
		this.password = password;
		this.regdt = regdt;
	}

	public String getUserId() {
		return userid;
	}

	public void setUserId(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegdt() {
		return regdt;
	}

	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}

	@Override
	public String toString() {
		return "UserVO [userid=" + userid + ", name=" + name + ", password=" + password + ", regdt=" + regdt + "]";
	}
	
}
