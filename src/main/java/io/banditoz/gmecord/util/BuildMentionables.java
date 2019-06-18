package io.banditoz.gmecord.util;

import io.banditoz.gmecord.Bot;
import io.banditoz.gmecord.SettingsManager;
import io.banditoz.gmecord.api.Member;
import io.banditoz.gmecord.api.Response;
import net.dv8tion.jda.core.entities.User;
import okhttp3.Request;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class BuildMentionables {
    public static HashMap<String, String> buildGroupmeMentionables() throws IOException {
        HashMap<String, String> mentionables = new HashMap<>();
        String initialUrl = "https://api.groupme.com/v3/groups/" + SettingsManager.getInstance().getSettings().getGroupID() + "?token=" + SettingsManager.getInstance().getSettings().getGroupMeToken();
        Request request = new Request.Builder()
                .url(initialUrl)
                .build();
        okhttp3.Response httpResponse = Bot.client.newCall(request).execute();
        Response r = SerializerDeserializer.deserializeResponseGivenString(Objects.requireNonNull(httpResponse.body()).string());
        for (Member m : r.getResponse().getMembers()) {
            mentionables.put(m.getNickname(), m.getUserId());
        }
        return mentionables;
    }

    public static HashMap<String, String> buildDiscordMentionables() {
        List<net.dv8tion.jda.core.entities.Member> members = Bot.getJda().getTextChannelById(SettingsManager.getInstance().getSettings().getChannel()).getGuild().getMembers();
        HashMap<String, String> mentionables = new HashMap<>();
        for (net.dv8tion.jda.core.entities.Member member : members) {
            User u = member.getUser();
            mentionables.put(u.getName(), u.getId());
        }
        return mentionables;
    }
}
