package checkValid;

public class inputValid {
    public static final int PASSWORD_LENGTH = 6;
    private static boolean isLetter(char ch) {
        return (ch >= 'a' && ch <= 'z');
    }

    private static boolean isUpperCase(char ch) {
        return (ch >= 'A' && ch <= 'Z');
    }

    private static boolean isNumber(char ch) {
        return (ch >= '0' && ch <= '9');
    }

    public static boolean isPassValid(String PASS) {
        int numCounter = 0, upperCounter = 0, letterCounter = 0;

        if (PASS.length() < PASSWORD_LENGTH) return false;

        for (int i = 0; i < PASS.length(); i++) {
            char ch = PASS.charAt(i);

            if (isUpperCase(ch)) upperCounter++;
            else if (isNumber(ch)) numCounter++;
            else if (isLetter(ch)) letterCounter++;
            else return false;
        }
        return (numCounter >= 1 && upperCounter >= 1 && letterCounter >= 1);
    }

    public static boolean isIdValid(String id) {
        if (id.length() != 12)  {
            return false;
        }
        else {
            for (int i = 0; i < id.length(); i++) {
                char ch = id.charAt(i);
                if (!isNumber(ch)) {
                    return false;
                }
            }
        }
        return true;
    }
}
