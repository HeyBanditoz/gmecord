package io.banditoz.gmecord.paste;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PasteResponse {
    @JsonProperty("status")
    private String status;
    @JsonProperty("result")
    private Result result;

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("result")
    public Result getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(Result result) {
        this.result = result;
    }
}

class Result {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("visibility")
    private String visibility;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("expires")
    private String expires;
    @JsonProperty("files")
    private List<FileResponse> files = null;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

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

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
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
    public List<FileResponse> getFiles() {
        return files;
    }

    @JsonProperty("files")
    public void setFiles(List<FileResponse> files) {
        this.files = files;
    }
}
class FileResponse {
    @JsonProperty("highlight_language")
    private Object highlightLanguage;
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;

    @JsonProperty("highlight_language")
    public Object getHighlightLanguage() {
        return highlightLanguage;
    }

    @JsonProperty("highlight_language")
    public void setHighlightLanguage(Object highlightLanguage) {
        this.highlightLanguage = highlightLanguage;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }
}