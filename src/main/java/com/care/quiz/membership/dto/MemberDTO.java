package com.care.quiz.membership.dto;

public class MemberDTO extends LoginDTO{
	
	private String pwOk;
	private String gender;
	private String email;
	
	
	public String getPwOk() {
		return pwOk;
	}
	public void setPwOk(String pwOk) {
		this.pwOk = pwOk;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
