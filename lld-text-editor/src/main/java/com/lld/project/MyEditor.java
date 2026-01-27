package com.lld.project;

import java.util.LinkedList;
import java.util.List;

public class MyEditor {
    protected List<List<EditorChar>> lines;
    private CommandHandler commandHandler;

    public MyEditor() {
        this.lines = new LinkedList<>();
        this.commandHandler = new CommandHandler();
    }

    public void addCharacter(Integer row, Integer column, Character ch) {
        AddCharacterCommand addCharacterCommand = new AddCharacterCommand(this, row, column, ch, "Sans Seif", 11,
                false, false);
        this.commandHandler.push(addCharacterCommand);

        // this.addCharacter(row, column, ch, "Sans Seif", 11, false, false);
    }

    public void addCharacter(Integer row, Integer column, Character ch,
            String fontName, Integer fontSize,
            boolean isBold, boolean isItalic) {
        AddCharacterCommand addCharacterCommand = new AddCharacterCommand(this, row, column, ch, fontName, fontSize,
                isBold, isItalic);
        this.commandHandler.push(addCharacterCommand);
        // // check if `row` exists in editor else add them

        // // Create row # of lines , if not exists
        // while (this.lines.size() <= row + 1) {
        // this.lines.add(new LinkedList<EditorChar>());
        // }
        // // System.out.printf("Lines size : %d", lines.size());

        // List<EditorChar> line = lines.get(row);

        // while (line.size() <= column + 1) {
        // line.add(new EditorChar());
        // }
        // line.set(column, new EditorChar(isBold, isItalic, fontSize, fontName, ch));
    }

    public void deleteCharacter(Integer row, Integer col) {
        DeleteCharacterCommand deleteCharacterCommand = new DeleteCharacterCommand(this, row, col);
        this.commandHandler.push(deleteCharacterCommand);

        // if (this.lines.size() < row) {
        // System.out.printf("AccessInvalidLine | Tried accessing line %d but only have
        // %d lines", row,
        // this.lines.size());

        // return false;
        // }

        // List<EditorChar> line = lines.get(row);
        // if (line.size() < col) {
        // System.out.printf("AccessInvalidChar | Tried accessing char at index %d but
        // only have %d chars", col,
        // line.size());

        // return false;
        // }

        // line.remove(col);
        // return true;
    }

    public String readLine(Integer row) {
        if (this.lines.size() < row) {
            System.out.printf("AccessInvalidLine | Tried accessing line %d but only have %d lines", row,
                    this.lines.size());

            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (EditorChar char1 : this.lines.get(row)) {
            sb.append(char1.getCh());
        }
        return sb.substring(1);
    }

    public String getStyle(Integer row, Integer col) {
        if (this.lines.size() < row) {
            System.out.printf("AccessInvalidLine | Tried accessing line %d but only have %d lines", row,
                    this.lines.size());

            return "";
        }

        List<EditorChar> line = lines.get(row);
        if (line.size() < col) {
            System.out.printf("AccessInvalidChar | Tried accessing char at index %d but only have %d chars", col,
                    line.size());

            return "";
        }

        return line.get(col).getFontName();
    }

    public void undo() {
        this.commandHandler.undo();
    }

}
