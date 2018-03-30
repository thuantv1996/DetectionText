package Core;

/**
 * Created by thuan on 3/29/2018.
 */

/**
 * Mô tả một khối hình ảnh lấy ra khi detect text
 */

import android.graphics.Bitmap;
public class Block {
    Bitmap image;
    MyRect Position;

    public Block(Bitmap image, MyRect position) {
        this.image = image;
        Position = position;
    }

    public Block() {
        Position = new MyRect();
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public MyRect getPosition() {
        return Position;
    }

    public void setPosition(MyRect position) {
        Position = position;
    }
}
