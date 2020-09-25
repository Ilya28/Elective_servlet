package org.elective.dbtools;

/**
 * Java - SQL naming converter
 */
public class NamesConverter {
    /**
     * Convert string with SQL naming standard (ex: newName -> new_name)
     * @param javaName String with Java naming rules
     * @return String with SQL naming rules
     */
    public static String toSQLName(String javaName) {
        StringBuilder result = new StringBuilder(javaName.length()*2);
        char[] charArray = javaName.toCharArray();
        result.append(Character.toLowerCase(charArray[0]));
        for (int i = 1; i < charArray.length; i ++) {
            if (
                    Character.isUpperCase(charArray[i]) &&
                    Character.isLowerCase(charArray[i-1]))
                result.append('_');
            if (i < charArray.length-1)
                if (
                        Character.isUpperCase(charArray[i]) &&
                        Character.isUpperCase(charArray[i-1]) &&
                        Character.isLowerCase(charArray[i+1]))
                    result.append('_');
            result.append(Character.toLowerCase(charArray[i]));
        }
        return result.toString();
    }
}
