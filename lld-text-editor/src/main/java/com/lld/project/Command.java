package com.lld.project;

import java.util.List;

public abstract class Command {
    protected MyEditor editor;
    protected List<List<EditorChar>> previousState;

    public Command(MyEditor editor) {
        this.editor = editor;
    }

    public abstract void execute();

    public void undo() {
        // System.out.printf("[undo] before undo state is %s %n", this.editor.lines );
        // System.out.printf("[undo] prev state is %s %n", previousState );
        
        this.editor.lines = previousState;
    }
}
