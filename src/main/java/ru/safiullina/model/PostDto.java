package ru.safiullina.model;

public class PostDto {
    private long id;
    private String content;
    private boolean removed;

    public PostDto() {
    }

    public PostDto(long id, String content, boolean removed) {
        this.id = id;
        this.content = content;
        this.removed = removed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
}
