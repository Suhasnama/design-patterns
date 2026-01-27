package com.lld.project;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteCharacterCommand extends Command {

    private Integer row;
    private Integer col;

    public DeleteCharacterCommand(MyEditor editor, Integer row, Integer col) {
        super(editor);
        this.row = row;
        this.col = col;
    }

    @Override
    public void execute() {

        // this.previousState = this.editor.lines;
        this.previousState = this.editor.lines.stream().map(LinkedList::new).collect(Collectors.toList());

        if (this.editor.lines.size() < this.row) {
            System.out.printf("AccessInvalidLine | Tried accessing line %d but only have %d lines", row,
                    this.editor.lines.size());

        }

        List<EditorChar> line = this.editor.lines.get(this.row);
        if (line.size() < this.col) {
            System.out.printf("AccessInvalidChar | Tried accessing char at index %d but only have %d chars", col,
                    line.size());

        }

        line.remove(this.col);
    }

}
