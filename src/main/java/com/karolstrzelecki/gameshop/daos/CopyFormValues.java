package com.karolstrzelecki.gameshop.daos;

import java.util.List;

public class CopyFormValues {
    public List<String> conditions;
    public List<String> platforms;

    @Override
    public String toString() {
        return "CopyFormValues{" +
                "conditions=" + conditions +
                ", platforms=" + platforms +
                '}';
    }
}
