package com.lld.project;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AddCharacterCommand extends Command {
    private Integer row;
    private Integer column;
    private Character ch;

    private String fontName;
    private Integer fontSize;

    private boolean isBold;
    private boolean isItalic;

    // private List<
    public AddCharacterCommand(MyEditor editor, Integer row, Integer column, Character ch,
            String fontName, Integer fontSize, boolean isBold, boolean isItalic) {
        super(editor);
        this.row = row;
        this.column = column;
        this.ch = ch;
        this.fontName = fontName;
        this.fontSize = fontSize;
        this.isBold = isBold;
        this.isItalic = isItalic;
    }

    @Override
    public void execute() {

        // Do this to create a new copy of the entire `this.editor.lines` object as java objects are passed by value of object reference.
        this.previousState = this.editor.lines.stream().map(LinkedList::new).collect(Collectors.toList());

        // System.out.printf("[execute] prev state is %s %n", previousState);

        // check if `row` exists in editor else add them

        // Create row # of lines , if not exists
        while (this.editor.lines.size() <= row) {
            this.editor.lines.add(new LinkedList<EditorChar>());
        }
        // System.out.printf("Lines size : %d", lines.size());

        List<EditorChar> line = this.editor.lines.get(row);

        while (line.size() <= column + 1) {
            line.add(new EditorChar());
        }
        line.set(column, new EditorChar(isBold, isItalic, fontSize, fontName, ch));

        // System.out.printf("[execute] post state is %s %n", previousState);
    }

}
