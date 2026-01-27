package com.lld.project;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        MyEditor editor = new MyEditor();
        editor.addCharacter(1, 1, 'S');
        System.out.printf("First line is : %s%n", editor.readLine(1));
        // editor.undo();
        editor.addCharacter(1, 1, 'S');
        // System.out.println("Pre undo : " +editor.readLine(1));
        // System.out.println("Post undo : " + editor.readLine(1));
        // System.out.printf("First line is : %s%n", editor.readLine(1));
        editor.addCharacter(1, 1, 'S');
        editor.addCharacter(1, 2, 'I');
        editor.addCharacter(1, 3, 'T');
        editor.addCharacter(1, 4, 'A');
        editor.addCharacter(1, 5, 'A');
        editor.addCharacter(1, 6, ' ');
        editor.addCharacter(1, 7, 'R');
        editor.addCharacter(1, 8, 'A');
        editor.addCharacter(1, 9, 'M');
        System.out.println(editor.readLine(1));
        // System.out.printf("First line is : %s", editor.readLine(1));

    }

}

/*
 * Editor
 * Dynamic Grid of Cells
 * 
 * init(Helper09 helper)
 * addCharacter(int row, int column, char ch,
 * String fontName, int fontSize,
 * boolean isBold, boolean isItalic)
 * String getStyle(int row, int col)
 * String getStyle(int row, int col)
 * boolean deleteCharacter(int row, int col)
 * String readLine(int row)
 * - returns all characters which are in row as string
 * - return example : "abcd"
 * - if there are not characters added in row then
 * empty string "" is returned.
 * 
 * 
 * Cell
 * - Char
 * - Bold
 * - Italic
 * - FontSize
 * 
 * 
 */
