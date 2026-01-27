package com.lld.project;

import java.util.LinkedList;
import java.util.List;

public class CommandHandler {
    private List<Command> history;

    public CommandHandler() {
        this.history = new LinkedList<>();
    }

    public void push(Command command) {
        this.history.add(command);
        command.execute();
    }

    public boolean undo() {
        if (this.history.size() > 0) {
            
            this.history.getLast().undo();
            this.history.removeLast();
            return true;
        }
        return false;
    }

}
