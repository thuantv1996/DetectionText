package Ocr;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by thuan on 3/29/2018.
 */

public interface IOcrService {
    public void create(AppCompatActivity instance);
    public void copyFile(AppCompatActivity instance);
    public String getContent(Bitmap img);
    public String[] getInfo(Bitmap img);
}
