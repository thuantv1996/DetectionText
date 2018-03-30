package Core;

/**
 * Created by thuan on 3/29/2018.
 */

/**
 * Định nghĩa một ký tự trích xuất từ tesseract
 * Character bao gồm: Char,Position.
 * Character có các phương thức get, set và getSize()
 */
public class Character {
    private char Char;
    private MyRect Position;

    public Character(char aChar, MyRect position) {
        Char = aChar;
        Position = position;
    }

    public Character() {
        Position = new MyRect();
    }

    public char getChar() {
        return Char;
    }

    public int getSize()
    {
        return Position.getHeight();
    }

    public void setChar(char aChar) {
        Char = aChar;
    }

    public MyRect getPosition() {
        return Position;
    }

    public void setPosition(MyRect position) {
        Position = position;
    }
}
