package io.banditoz.gmecord.util;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.ArrayList;
import java.util.Arrays;

public class EmbedFormatter {
    /**
     * Formats a MessageEmbed and returns a okay-looking string representation of it.
     * @param e The MessageEmbed to format.
     * @return The formatted embed object.
     */
    public static String formatEmbed(MessageEmbed e) {
        if (e.getTitle() == null && e.getDescription() == null) {
            // Probably a link, skip it.
            return "";
        }
        ArrayList<String> strings = new ArrayList<>();
        strings.add(e.getTitle() + ((e.getAuthor() == null) ? "" : ", " + e.getAuthor().getName()));
        for (MessageEmbed.Field f : e.getFields()) {
           strings.add(f.getName() + ": " + f.getValue());
        }
        if (e.getDescription() != null) {
            strings.addAll(Arrays.asList(e.getDescription().split("\r?\n")));
        }
        if (e.getFooter() != null) {
            strings.add(e.getFooter().getText());
        }

        StringBuilder response = new StringBuilder();
        String[] stringArray = new String[strings.size()];
        for (int i = 0; i < strings.size(); i++) {
            stringArray[i] = strings.get(i);
        }

        for (int i = 0; i < stringArray.length; i++) {
            if (i == 0) {
                stringArray[i] = "╽ " + stringArray[i] + "\n";
            }
            else if (i == stringArray.length - 1) {
                stringArray[i] = "╿ " + stringArray[i] + "\n";
            }
            else {
                stringArray[i] = "┃ " + stringArray[i] + "\n";
            }
            response.append(stringArray[i]);
        }
        return response.toString();
    }
}
