package com.karolstrzelecki.gameshop.models.Copy;

import java.util.HashMap;
import java.util.Map;

public enum Platform {
    pc("PC"),
    ps4("PS4"),
    xbox("XBOX"),
    ninntendo("Nintendo");

    public final String name;

    Platform(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private static final Map<String, Platform> lookup = new HashMap<>();

    static {
        for(Platform p : Platform.values()){
            lookup.put(p.getName(), p);
        }
    }
    public static Platform get(String str){
        return lookup.get(str);
    }
}
