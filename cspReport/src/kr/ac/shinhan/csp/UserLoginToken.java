package kr.ac.shinhan.csp;


import java.util.UUID;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class UserLoginToken {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long keyU;
	@Persistent
	private String token;
	@Persistent
	private String userAccount;
	@Persistent
	private String expireDate;
	
	
	public UserLoginToken(String token, String userAccount, String expireDate) {
		super();
		this.token = token;
		//this.token =  UUID.randomUUID().toString();
		this.userAccount = userAccount;
		this.expireDate = expireDate;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
		// this.expireDate = Date.getDay() + 30;
	}
	public Long getKeyU() {
		return keyU;
	}
	
	
}
