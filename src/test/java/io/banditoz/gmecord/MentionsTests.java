package io.banditoz.gmecord;

import io.banditoz.gmecord.api.GroupmeMessage;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class MentionsTests { // TODO: Write mentions test for Groupme.
    @Test public void discordMentionsShouldBeBuiltCorrectly() {
        String sampleMessage = "@JohnDoe @Jane Doe hello! how are you guys doing @FooBar?";
        String expected = "**<Example User>** <@163094867910590900> <@163014667920590902> hello! how are you guys doing <@163614687920290107>?";

        GroupmeMessage gMessage = new GroupmeMessage();
        gMessage.setText(sampleMessage);
        gMessage.setName("Example User");
        HashMap<String, String> mentionables = new HashMap<>();
        mentionables.put("JohnDoe", "163094867910590900");
        mentionables.put("Jane Doe", "163014667920590902");
        mentionables.put("FooBar", "163614687920290107");
        Bot.setMentionableDiscord(mentionables);
        DiscordMessageCreator message = new DiscordMessageCreator(gMessage, false);
        message.build();
        assertEquals(expected, message.createMessage());
    }
}
