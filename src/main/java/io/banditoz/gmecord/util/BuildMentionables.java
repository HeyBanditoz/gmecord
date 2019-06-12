package io.banditoz.gmecord.util;

import io.banditoz.gmecord.Bot;
import io.banditoz.gmecord.SettingsManager;
import io.banditoz.gmecord.api.Member;
import io.banditoz.gmecord.api.Response;
import net.dv8tion.jda.core.entities.User;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class BuildMentionables {
    public static HashMap<String, String> buildGroupmeMentionables() {
        HashMap<String, String> mentionables = new HashMap<>();
        CloseableHttpClient client = HttpClients.createDefault();
        String initialUrl = "https://api.groupme.com/v3/groups/" + SettingsManager.getInstance().getSettings().getGroupID() + "?token=" + SettingsManager.getInstance().getSettings().getGroupMeToken();
        HttpGet get = new HttpGet(initialUrl);
        CloseableHttpResponse response;
        try {
            response = client.execute(get);
            String responseString = EntityUtils.toString(response.getEntity());
            Response r = DeserializeResponse.deserializeGivenString(responseString);
            for (Member m : r.getResponse().getMembers()) {
                mentionables.put(m.getNickname(), m.getUserId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            get.reset();
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
