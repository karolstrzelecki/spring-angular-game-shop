package com.karolstrzelecki.gameshop.models;


import java.util.HashMap;
import java.util.Map;

public enum GameCategory {
    action("Akcja"),
    rpg("RPG"),
    simulators("Symulacje"),
    fighting("Bijatyki"),
    adventure("Przygodowe"),
    rts("Strategiczne"),
    fps("FPS"),
    racing("Wyścigowe"),
    fictional("Fabularne"),
    arcade("Zręcznościowe"),
    vr("VR");

    public final String name;

    private GameCategory(String name) {
        this.name  = name;
    }

    public String getName() {
        return name ;
    }

    private static final Map<String, GameCategory> lookup = new HashMap<>();

    static
    {
        for(GameCategory gc : GameCategory.values()){
            lookup.put(gc.getName(), gc);
        }
    }

    public static GameCategory get(String str){
        return lookup.get(str);
    }

}

