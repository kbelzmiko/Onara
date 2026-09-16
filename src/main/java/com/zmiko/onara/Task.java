package com.zmiko.onara;

public class Task {
    private int id;
    private String desc;

    public Task(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int getId() { return this.id; }

    public String getDesc() { return this.desc; }
}
