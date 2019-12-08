package io.banditoz.gmecord;

import io.banditoz.gmecord.api.Attachment;
import io.banditoz.gmecord.api.GroupmeMessage;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class Tests { // TODO: Write mentions test for Groupme.
    private HashMap<String, String> groupmeMentionables;

    @Before public void initialize() {
        groupmeMentionables = new HashMap<>();
        groupmeMentionables.put("JohnDoe", "163094867910590900");
        groupmeMentionables.put("Jane Doe", "163014667920590902");
        groupmeMentionables.put("FooBar", "163614687920290107");
    }

    @Test public void discordMentionsShouldBeBuiltCorrectly() {
        String sampleMessage = "@JohnDoe @Jane Doe hello! how are you guys doing @FooBar?";
        String expected = "**<Example User>** <@163094867910590900> <@163014667920590902> hello! how are you guys doing <@163614687920290107>?";

        GroupmeMessage gMessage = new GroupmeMessage();
        gMessage.setText(sampleMessage);
        gMessage.setName("Example User");

        Bot.setMentionableDiscord(groupmeMentionables);
        DiscordMessageCreator message = new DiscordMessageCreator(gMessage, false);

        assertEquals(expected, message.getMessage());
    }

    @Test public void locationAttachmentShouldBeBuiltCorrectly() {
        String expected = "**<John Doe>** <LOCATION> Latitude: 40.777404, Longitude: -111.888207, Name: Capitol Building, Google Maps URL: http://maps.google.com/maps?q=40.777404,-111.888207 Look at my location!";
        String actual;

        Attachment attachment1 = new Attachment();
        attachment1.setType("location");
        attachment1.setLat("40.777404");
        attachment1.setLng("-111.888207");
        attachment1.setName("Capitol Building");
        Attachment[] attachments = new Attachment[1];
        attachments[0] = attachment1;

        GroupmeMessage message1 = new GroupmeMessage();
        message1.setText("Look at my location!");
        message1.setName("John Doe");
        message1.setAttachments(attachments);

        DiscordMessageCreator creator = new DiscordMessageCreator(message1, false);
        actual = creator.getMessage();

        assertEquals(expected, actual);
    }

    @Test public void imageAttachmentShouldBeBuiltCorrectly() {
        String expected = "**<John Doe>** <IMAGE> URL: https://knockout.chat/static/logo.png Look at this crazy image!";
        String actual;

        Attachment attachment1 = new Attachment();
        attachment1.setType("image");
        attachment1.setUrl("https://knockout.chat/static/logo.png");
        Attachment[] attachments = new Attachment[1];
        attachments[0] = attachment1;

        GroupmeMessage message1 = new GroupmeMessage();
        message1.setText("Look at this crazy image!");
        message1.setName("John Doe");
        message1.setAttachments(attachments);

        DiscordMessageCreator creator = new DiscordMessageCreator(message1, false);
        actual = creator.getMessage();

        assertEquals(expected, actual);
    }

    @Test public void spoilersShouldBeCreatedCorrectly() {
        String expected = "<John Doe> Hey! Did you see that new episode of ■■■ Did you see the part when ■■■";
        String actual;

        Bot.setMentionableGroupme(new HashMap<>());
        GroupmeMessageCreator creator = new GroupmeMessageCreator(expected, false);
        actual = creator.getMessage().getText();

        assertEquals(expected, actual);
    }
}
