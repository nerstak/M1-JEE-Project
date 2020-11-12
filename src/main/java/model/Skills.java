package model;

import java.util.UUID;

public class Skills {
    private String skill;

    public Skills(String skill, UUID uuid) {
        this.skill = skill;
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    private UUID uuid;

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
