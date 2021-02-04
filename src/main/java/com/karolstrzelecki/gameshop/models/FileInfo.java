package com.karolstrzelecki.gameshop.models;

import java.nio.file.Path;
import java.util.Arrays;

public class FileInfo {

    public String name;
    public byte[] picbyte;
    public Path path;

    public FileInfo(String name, byte[] picbyte) {
        this.name = name;
        this.picbyte = picbyte;
    }

    public FileInfo(String name, byte[] picbyte, Path path) {
        this.name = name;
        this.picbyte = picbyte;
        this.path = path;
    }

    public FileInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPicbyte() {
        return picbyte;
    }

    public void setPicbyte(byte[] picbyte) {
        this.picbyte = picbyte;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "name='" + name + '\'' +
                ", picbyte="  +
                '}';
    }
}
