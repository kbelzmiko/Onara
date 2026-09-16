package com.zmiko.onara;

import java.util.ArrayList;

public class TaskLogic {
    private ArrayList<Task> taskList;
    private mode mode;
    private final DatabaseControl db = new DatabaseControl();

    public TaskLogic() {
        if (!db.isEmpty()) {
            this.taskList = db.getFromDatabase();
        } else {
            this.taskList = new ArrayList<Task>();
        }
    }


    public void push(String task) {
        this.taskList.add(new Task(this.db.getLastId()+1,task));
        this.db.insert(task);
    }


    public Task popQueue() {
        if (!this.taskList.isEmpty()) {
            this.db.pop(this.taskList.get(0).getId());
            return this.taskList.remove(0);
        } else {
            return null;
        }
    }

    public Task popStack() {
        if (!this.taskList.isEmpty()) {
            this.db.pop(this.taskList.get(this.taskList.size()-1).getId());
            return this.taskList.remove(this.taskList.size()-1);
        } else {
            return null;
        }
    }

    public ArrayList<Task> getTasks() {
        return this.taskList;
    }


}
