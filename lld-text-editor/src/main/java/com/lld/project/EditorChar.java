package com.lld.project;

public class EditorChar {
    private boolean isBold;
    private boolean isItalic;
    private Integer fontSize;
    private String fontName;
    private Character ch;

    public EditorChar(boolean isBold, boolean isItalic, Integer fontSize, String fontName, Character ch) {
        this.isBold = isBold;
        this.isItalic = isItalic;
        this.fontSize = fontSize;
        this.fontName = fontName;
        this.ch = ch;
    }

    public EditorChar() {
        this(false, false, 11, "Sans Serif", ' ');
    }

    public EditorChar(Character ch) {
        this(false, false, 11, "Sans Serif", ch);
    }

    @Override
    public String toString() {
        return "\nEditorChar [isBold=" + isBold + ", isItalic=" + isItalic + ", fontSize=" + fontSize + ", fontName="
                + fontName + ", ch=" + ch + "]\n";
    }

    public boolean isBold() {
        return isBold;
    }

    public boolean isItalic() {
        return isItalic;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public String getFontName() {
        return fontName;
    }

    public Character getCh() {
        return ch;
    }

}
