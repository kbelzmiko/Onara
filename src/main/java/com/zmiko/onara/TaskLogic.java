package com.zmiko.onara;

import java.util.ArrayList;

public class TaskLogic {
    private ArrayList<String> taskList;
    private mode mode;

    public TaskLogic() {
        this.taskList = new ArrayList<String>();
    }

    public TaskLogic(ArrayList<String> taskFromDB) {
        this.taskList = taskFromDB;
    }

    public void pushQueue(String task) {
        this.taskList.add(task);
    }

    public void pushStack(String task) {
        if (!this.taskList.isEmpty()) {
            this.taskList.add(0,task);
        } else {this.taskList.add(task);}
    }

    public String popQueue() {
        if (!this.taskList.isEmpty()) {
            return this.taskList.remove(this.taskList.size()-1);
        } else {
            return null;
        }
    }

    public String popStack() {
        if (!this.taskList.isEmpty()) {
            return this.taskList.remove(0);
        } else {
            return null;
        }
    }

    public ArrayList<String> getTasks() {
        return this.taskList;
    }


}
