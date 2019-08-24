package io.banditoz.gmecord.util;

public class MarkdownUtils {
    /**
     * @see <a href="https://github.com/DV8FromTheWorld/JDA/issues/1086">https://github.com/DV8FromTheWorld/JDA/issues/1086</a>
     * Properly escapes all possible markdown characters.
     * @param str The string to escape markdown characters.
     * @return The escaped string.
     */
    public static String escapeMarkdownCharacters(String str) {
        return str.replace("*", "\\*")
                .replace("_", "\\_")
                .replace("|", "\\|")
                .replace("~", "\\~")
                .replace("`", "\\`");
    }
}
