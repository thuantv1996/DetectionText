package Core;

/**
 * Created by thuan on 3/29/2018.
 */


/**
 * Định nghĩa một vùng hình chữ nhật với tọa độ top, left, bottom ,right
 */
public class MyRect {

    private int left;
    private int top;
    private int bottom;
    private int right;

    public MyRect(int top, int left, int bottom, int right) {
        this.left = left;
        this.top = top;
        this.bottom = bottom;
        this.right = right;
    }

    public MyRect() {
    }

    public int getLeft() {
        return left;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    public int getRight() {
        return right;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getWidth() {
        return right - left;
    }

    public int getHeight() {
        return bottom - top;
    }
}
