package cn.com.icloud.retention;

public class PasswordUtils {
	@UserCase(id = 47, description = "Passwords must contain at least one numeric")
	public boolean validatePassword(String password) {
		return (password.matches("\\w*\\d\\w*"));
	}

	@UserCase(id = 48)
	public String encryptPassword(String password) {
		return new StringBuilder(password).reverse().toString();
	}
}
