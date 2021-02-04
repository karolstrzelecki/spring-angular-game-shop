package com.karolstrzelecki.gameshop.models;

public enum Language {
    Pl("Polski"),
    Eng("Angielski"),
    De("Hiszpański"),
    Rus("Rosyjski");

    public final String description;

    Language(String description) {
        this.description = description;
    }
}
