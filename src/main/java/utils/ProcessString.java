package utils;

public class ProcessString {
    /**
     * Check if a set of string are empty or not
     * @param strings, an array of string to check
     * @return true if at least one string is empty, else return false
     */
    public static boolean areStringEmpty(String... strings){
        for (String str : strings) {
            if (str.isEmpty())
                return true;
        }
        return false;
    }

    public static String capitalizeAndLowerCase(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
