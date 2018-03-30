package Core;

/**
 * Created by thuan on 3/29/2018.
 */


import java.util.ArrayList;
import java.util.List;

/**
 * Định nghĩa một dòng trích xuất được từ tesseract
 * Bao gồm: ListWord, Position, Content.
 * Phương thức: get,set, getMinSize(),getMaxSize()
 */
public class Line {
    List<Word> listWords;
    MyRect Position;
    String Content;

    public Line() {
        Position = new MyRect();
        listWords = new ArrayList<>();
        Content = "";
    }

    public List<Word> getListWords() {
        return listWords;
    }


    public MyRect getPosition() {
        return Position;
    }

    public void setPosition(MyRect position) {
        Position = position;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getMinSize() {
        // if lst empty return 0
        // min = lst[0]
        // i = 1 -> size(lst)
        // min = lst[i]<min?lst[i]:min
        // return min
        if (listWords == null || listWords.isEmpty()) {
            return 0;
        }
        int min = listWords.get(0).getMinSize();
        for (int i = 1; i < listWords.size(); i++) {
            int value = listWords.get(i).getMinSize();
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
        if (listWords == null || listWords.isEmpty()) {
            return 0;
        }
        int max = listWords.get(0).getMaxSize();
        for (int i = 1; i < listWords.size(); i++) {
            int value = listWords.get(i).getMaxSize();
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
