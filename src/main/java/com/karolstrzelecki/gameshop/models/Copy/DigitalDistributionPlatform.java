package com.karolstrzelecki.gameshop.models.Copy;

public enum DigitalDistributionPlatform {
    amazon("Amazon Digital Services"),
    microsoft("Games for Windows-Live"),
    steam("Steam"),
    origin("Origin"),
    battlenet("Battle.net"),
    gog("GOG.com");

    public final String description;

    DigitalDistributionPlatform(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
