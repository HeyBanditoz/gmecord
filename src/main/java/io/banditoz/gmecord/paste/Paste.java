package io.banditoz.gmecord.paste;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("visibility")
    private String visibility;
    @JsonProperty("expires")
    private String expires;
    @JsonProperty("files")
    private List<File> files = null;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("visibility")
    public String getVisibility() {
        return visibility;
    }

    @JsonProperty("visibility")
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @JsonProperty("expires")
    public String getExpires() {
        return expires;
    }

    @JsonProperty("expires")
    public void setExpires(String expires) {
        this.expires = expires;
    }

    @JsonProperty("files")
    public List<File> getFiles() {
        return files;
    }

    @JsonProperty("files")
    public void setFiles(List<File> files) {
        this.files = files;
    }

}

class Content {
    @JsonProperty("format")
    private String format;
    @JsonProperty("highlight_language")
    private Object highlightLanguage;
    @JsonProperty("value")
    private String value;
    @JsonProperty("content")
    private String content;

    @JsonProperty("format")
    public String getFormat() {
        return format;
    }

    @JsonProperty("format")
    public void setFormat(String format) {
        this.format = format;
    }

    @JsonProperty("highlight_language")
    public Object getHighlightLanguage() {
        return highlightLanguage;
    }

    @JsonProperty("highlight_language")
    public void setHighlightLanguage(Object highlightLanguage) {
        this.highlightLanguage = highlightLanguage;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(String content) {
        this.content = content;
    }
}

class File {
    @JsonProperty("name")
    private String name;
    @JsonProperty("content")
    private Content content;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("content")
    public Content getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(Content content) {
        this.content = content;
    }
}