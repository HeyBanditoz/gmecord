package io.banditoz.gmecord.util;

import io.banditoz.gmecord.Bot;
import io.banditoz.gmecord.SettingsManager;
import io.banditoz.gmecord.api.Member;
import io.banditoz.gmecord.api.Response;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class BuildMentionables {
    private static final Logger LOGGER = LoggerFactory.getLogger(BuildMentionables.class);

    public static HashMap<String, String> buildGroupmeMentionables() throws IOException {
        HashMap<String, String> mentionables = new HashMap<>();
        String initialUrl = "https://api.groupme.com/v3/groups/" + SettingsManager.getInstance().getSettings().getGroupID() + "?token=" + SettingsManager.getInstance().getSettings().getGroupMeToken();
        Request request = new Request.Builder()
                .url(initialUrl)
                .build();
        okhttp3.Response httpResponse = Bot.getOkHttpClient().newCall(request).execute();
        Response r = SerializerDeserializer.deserializeResponseGivenString(Objects.requireNonNull(httpResponse.body()).string());
        for (Member m : r.getResponse().getMembers()) {
            mentionables.put(m.getNickname(), m.getUserId());
        }
        LOGGER.info("We have " + mentionables.size() + " Groupme members.");
        return mentionables;
    }

    public static HashMap<String, String> buildDiscordMentionables() {
        Guild g = Bot.getJda().getTextChannelById(SettingsManager.getInstance().getSettings().getChannel()).getGuild();
        g.retrieveMembers().join(); // ..you MUST NOT use join()... https://i.kym-cdn.com/entries/icons/mobile/000/024/196/sign.jpg
        HashMap<String, String> mentionables = new HashMap<>();
        for (net.dv8tion.jda.api.entities.Member member : g.getMembers()) {
            User u = member.getUser();
            mentionables.put(u.getName(), u.getId());
        }
        LOGGER.info("We have " + mentionables.size() + " Discord members.");
        return mentionables;
    }
}
