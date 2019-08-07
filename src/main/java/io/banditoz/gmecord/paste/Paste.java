package io.banditoz.gmecord.paste;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paste {
    public Paste(String message) {
        this.setVisibility("unlisted");
        File file = new File();
        Content content = new Content();
        content.setFormat("text");
        content.setValue(message);
        file.setContent(content);

        ArrayList<File> files = new ArrayList<>();
        files.add(file);
        this.setFiles(files);
    }

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("visibility")
    @Expose
    private String visibility;
    @SerializedName("expires")
    @Expose
    private String expires;
    @SerializedName("files")
    @Expose
    private List<File> files = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

}

class Content {
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("highlight_language")
    @Expose
    private Object highlightLanguage;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("content")
    @Expose
    private String content;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Object getHighlightLanguage() {
        return highlightLanguage;
    }

    public void setHighlightLanguage(Object highlightLanguage) {
        this.highlightLanguage = highlightLanguage;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

class File {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("content")
    @Expose
    private Content content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}