package sinqia.client;

import java.util.Objects;

public class Client{
	
	private String name;
	private String password;
	private String registrationId;

	public Client() {
	}
	
	public Client(String name) {
		this.name = name;
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
	
	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(registrationId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		return Objects.equals(this.registrationId, obj);
	}
	
	public boolean passwordIsEqualTo(String loginAttempt) {
		return this.password.equals(loginAttempt);
	}

}
