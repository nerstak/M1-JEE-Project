package model;

import java.util.UUID;

public class Keywords {
    private String keyword;

    public Keywords(String Keyword, UUID uuid) {
        this.keyword = Keyword;
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    private UUID uuid;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String Keyword) {
        this.keyword = Keyword;
    }
}
