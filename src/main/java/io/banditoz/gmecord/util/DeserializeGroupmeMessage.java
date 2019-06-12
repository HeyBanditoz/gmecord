package io.banditoz.gmecord.util;

import com.google.gson.Gson;
import io.banditoz.gmecord.api.GroupmeMessage;

public class DeserializeGroupmeMessage {
    public static GroupmeMessage deserializeGivenString(String json) {
        Gson gson = new Gson();
        GroupmeMessage object = gson.fromJson(json, GroupmeMessage.class);
        if (object.getName() == null || object.getText() == null) {
            throw new NullPointerException("Either GroupmeMessage.getName() or GroupmeMessage.getText() is null! This should not be a valid message!");
        }
        return object;
    }
}
