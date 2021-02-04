package com.karolstrzelecki.gameshop.models.Copy;

import java.util.HashMap;
import java.util.Map;

public enum Conditions {
    brandnew("Nowy"),
    verygood("Bardzo dobry"),
    good("Dobry"),
    acceptable("Średni");

    public final String description;

    public String getDescription() {
        return description;
    }

    Conditions(String description) {
        this.description = description;
    }

    private static final Map<String, Conditions> lookup = new HashMap<>();

    static {
        for(Conditions cond: Conditions.values()){
            lookup.put(cond.getDescription(), cond);
        }
    }

    public static Conditions get(String str){
        return lookup.get(str);
    }
}
