package Core;

/**
 * Created by thuan on 3/29/2018.
 */

/**
 * Định nghĩa một từ trích xuất được từ tesseract
 * Bao gồm: ListCharacter,Position
 * Phương thức: get,set, getMinSize(),getMaxSize()
 */

import java.util.ArrayList;
import java.util.List;

public class Word {
    private List<Character> listCharacter;
    private MyRect Position;

    public Word() {
        Position = new MyRect();
        listCharacter = new ArrayList<>();
    }

    public MyRect getPosition() {
        return Position;
    }

    public void setPosition(MyRect position) {
        Position = position;
    }

    public List<Character> getListCharacter() {
        return listCharacter;
    }

    public int getMinSize() {
        // if lst empty return 0
        // min = lst[0]
        // i = 1 -> size(lst)
        // min = lst[i]<min?lst[i]:min
        // return min
        if (listCharacter == null || listCharacter.isEmpty()) {
            return 0;
        }
        int min = listCharacter.get(0).getSize();
        for (int i = 1; i < listCharacter.size(); i++) {
            int value = listCharacter.get(i).getSize();
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    public int getMaxSize() {
        // if lst empty return 0
        // max = lst[0]
        // i = 1 -> size(lst)
        // max = lst[i]>max?lst[i]:max
        // return min
        if (listCharacter == null || listCharacter.isEmpty()) {
            return 0;
        }
        int max = listCharacter.get(0).getSize();
        for (int i = 1; i < listCharacter.size(); i++) {
            int value = listCharacter.get(i).getSize();
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

}
