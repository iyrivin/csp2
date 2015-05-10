package kr.ac.shinhan.csp;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class TeamMember {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long key;
	
	@Persistent
	private String name;
	@Persistent
	private String stdId;
	@Persistent
	private String phone;
	@Persistent
	private String email;
	@Persistent
	private String kakao;
	@Persistent
	private String gitId;
	@Persistent
	private boolean teamMan;
	
	public TeamMember(String name, String stdId, String phone, String email, String kakao, String gitId, boolean teamMan) {
		this.name = name;
		this.stdId = stdId;
		this.phone = phone;
		this.email = email;
		this.kakao = kakao;
		this.gitId = gitId;
		this.teamMan = teamMan;
	}

	public Long getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStdId() {
		return stdId;
	}

	public void setStdId(String stdId) {
		this.stdId = stdId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKakao() {
		return kakao;
	}

	public void setKakao(String kakao) {
		this.kakao = kakao;
	}

	public String getGitId() {
		return gitId;
	}

	public void setGitId(String gitId) {
		this.gitId = gitId;
	}

	public boolean isTeamMan() {
		return teamMan;
	}

	public void setTeamMan(boolean teamMan) {
		this.teamMan = teamMan;
	}
	
}
