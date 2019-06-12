package io.banditoz.gmecord;

import io.banditoz.gmecord.api.Attachment;
import io.banditoz.gmecord.api.GroupmeMessage;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class Tests { // TODO: Write mentions test for Groupme.
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
        creator.build();
        actual = creator.createMessage();

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
        creator.build();
        actual = creator.createMessage();

        assertEquals(expected, actual);
    }
}
