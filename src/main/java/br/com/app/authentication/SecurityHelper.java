package br.com.app.authentication;

import java.util.Base64;
import java.util.concurrent.TimeUnit;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import br.com.app.cache.LoginCacheObject;

public class SecurityHelper {

	private static final long TOKEN_VALIDITY = TimeUnit.DAYS.toMillis(7);

	private static final String key = "NoisFazemoChuver"; // 128 bit key
	private static final String initVector = "DeCabecaPraBaixo"; // 16 bytes IV

	private SecurityHelper() {
		// statics only
	}

	public static String encrypt(String text) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] secure = cipher.doFinal(text.getBytes());
			return Base64.getEncoder().encodeToString(secure);
		} catch (Exception e) { // Should never happen
			throw new RuntimeException(e);
		}
	}

	public static String decrypt(String text) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(Base64.getDecoder().decode(text));
			return new String(original);
		} catch (Exception e) {
			return null;
		}
	}

	public static String generateAdminServerToken(String user, String pass) {
		return encrypt(user + "|" + pass);
	}

	public static boolean isValidAdminServerToken(String token, FixedUser admin) {
		String plainText = decrypt(token);
		if (plainText == null) {
			return false;
		}
		String[] values = plainText.split("\\|");
		return new FixedUser(values[0], values[1]).equals(admin);
	}

	public static String generateAppToken(String user) {
		long validity = System.currentTimeMillis() + TOKEN_VALIDITY;
		String tokenText = validity + "|" + user;
		return encrypt(tokenText);
	}

	public static boolean isValidAppToken(String token, String user) {
		String plainText = decrypt(token);
		if (plainText == null) {
			return false;
		}

		String[] values = plainText.split("\\|");
		long tokenTime = Long.valueOf(values[0]);

		if (System.currentTimeMillis() > tokenTime) {
			return false;
		}

		return values[1].equals(user);
	}

	public static boolean isValidAppToken(LoginCacheObject loginCacheObject) {
		return System.currentTimeMillis() < loginCacheObject.getTokenValidity();
	}

	public static long getTokenValidity(String token) {
		String plainText = decrypt(token);
		String[] values = plainText.split("\\|");
		return Long.valueOf(values[0]);
	}

}
