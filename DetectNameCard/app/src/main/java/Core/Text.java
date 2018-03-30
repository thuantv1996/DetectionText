package Core;

/**
 * Created by thuan on 3/29/2018.
 */


import java.util.ArrayList;
import java.util.List;

/**
 * Định nghĩa một trang văn bản trích xuất được từ tesseract
 * Bao gồm: ListLine, Content.
 * Phương thức: get,set, getMinSize(),getMaxSize()
 */
public class Text {

    List<Line> listLines;
    String Content;

    public Text() {
        listLines = new ArrayList<>();
        Content = "";
    }

    public List<Line> getListLines() {
        return listLines;
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
        if (listLines == null || listLines.isEmpty()) {
            return 0;
        }
        int min = listLines.get(0).getMinSize();
        for (int i = 1; i < listLines.size(); i++) {
            int value = listLines.get(i).getMinSize();
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
        if (listLines == null || listLines.isEmpty()) {
            return 0;
        }
        int max = listLines.get(0).getMaxSize();
        for (int i = 1; i < listLines.size(); i++) {
            int value = listLines.get(i).getMaxSize();
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
