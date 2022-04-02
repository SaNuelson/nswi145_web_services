package util;

import java.util.ArrayList;
import java.util.List;

public class Random {

    private static java.util.Random random = new java.util.Random();

    private static String[] firstNames = new String[]{
            "Melody",
            "Gideon",
            "Ibrahim",
            "Izabella",
            "Armani",
            "Jaeden",
            "Arjun",
            "Keon",
            "Ali",
            "Kinley",
            "Carly",
            "Jaiden",
            "Casey",
            "Jovanny",
            "Alden",
            "Ryker",
            "Lance",
            "Giancarlo",
            "Elliott",
            "Tiara",
            "Susan",
            "Darien",
            "Jayleen",
            "Tony"
    };

    private static String[] lastNames = new String[] {
            "Tate",
            "Romero",
            "Schwartz",
            "Cline",
            "Pratt",
            "Richmond",
            "Odom",
            "Gardner",
            "Irwin",
            "Sellers",
            "Gross",
            "Oconnell",
            "Mcmillan",
            "Hancock",
            "Mays",
            "Maddox",
            "Estrada",
            "Gill",
            "Riddle",
            "Novak"
    };

    private static String[] dumbPasswords = new String[] {
            "123456",
            "123456789",
            "12345",
            "qwerty",
            "password",
            "12345678",
            "111111",
            "123123",
            "1234567890",
            "1234567",
            "qwerty123",
            "000000",
            "1q2w3e",
            "aa12345678",
            "abc123",
            "password1",
            "1234",
            "qwertyuiop",
            "123321",
            "password123"
    };

    public static String getRandomName() {
        return firstNames[random.nextInt(firstNames.length)] + " " + lastNames[random.nextInt(lastNames.length)];
    }

    public static String getRandomPass() {
        return dumbPasswords[random.nextInt(dumbPasswords.length)];
    }

    public static String[] getRandomSubset(String[] source) {
        List<String> result = new ArrayList<String>();
        for (String item : source) {
            if (random.nextFloat() > 0.5) {
                result.add(item);
            }
        }
        return result.toArray(new String[0]);
    }

    public static <T> T pickRandom(T[] source) {
        return source[random.nextInt(source.length)];
    }

    public static <T> T pickRandom(List<T> source) {
        return source.get(random.nextInt(source.size()));
    }

    public static int getRandomInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }

}
