package com.bizboar.superCoolBatchProgram.models;

public class ColorDTO {
    private String name;
    private String hex;
    private String lrv;

    public ColorDTO() {
    }

    public ColorDTO(String name, String hex, String lrv) {
        this.name = name;
        this.hex = hex;
        this.lrv = lrv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public String getLrv() {
        return lrv;
    }

    public void setLrv(String lrv) {
        this.lrv = lrv;
    }
}
