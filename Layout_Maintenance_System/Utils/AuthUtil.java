package Utils;

public class AuthUtil {

    private static final String ADMIN_PASSWORD = "admin@123";

    private AuthUtil() {}

    public static boolean validateAdmin(String inputPassword) {
        return ADMIN_PASSWORD.equals(inputPassword);
    }
}
