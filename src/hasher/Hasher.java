package hasher;

public class Hasher {
    public static String hashString(String text) {
        String hashedText = text.substring(0, 2);
        hashedText = hashedText + "****";
        String temp = text.substring(text.length() - 2, text.length());
        return hashedText + temp;
    }
}
