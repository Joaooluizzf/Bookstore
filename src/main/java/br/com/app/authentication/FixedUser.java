package br.com.app.authentication;

public final class FixedUser {

	private String name;
	private String pass;

	public FixedUser() {
	}

	public FixedUser(String usuario, String senha) {
		this.name = usuario;
		this.pass = senha;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj.getClass() != FixedUser.class) {
			return false;
		}

		FixedUser other = (FixedUser) obj;
		return getName().equals(other.getName()) && getPass().equals(other.getPass());
	}

	@Override
	public int hashCode() {
		return getName().hashCode() + getPass().hashCode();
	}

}
