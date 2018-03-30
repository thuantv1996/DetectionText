package Ocr;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import Ocr.IOcrService;

/**
 * Created by thuan on 3/29/2018.
 */

public class TesseractOcr implements IOcrService {
    // attribute
    TessBaseAPI baseAPI=null;
    // method
    @Override
    public void create(AppCompatActivity instance) {
        baseAPI =new TessBaseAPI();
        String dataPath = instance.getFilesDir().toString();
        baseAPI.init(dataPath,"eng");
    }

    @Override
    public void copyFile(AppCompatActivity instance) {
        try {
            File dir = new File(instance.getFilesDir() + "/tessdata");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File trainedData = new File(instance.getFilesDir() + "/tessdata/eng.traineddata");
            if (!trainedData.exists()) {
                processCopyFile(instance);
            }
        } catch (IOException e) {
        }

    }

    @Override
    public String getContent(Bitmap img) {
        baseAPI.setImage(img);
        return baseAPI.getUTF8Text();
        //return baseAPI.getBoxText(0);
    }

    @Override
    public String[] getInfo(Bitmap img) {
        baseAPI.setImage(img);
        return baseAPI.getBoxText(0).split("0\n");
    }

    private void processCopyFile(AppCompatActivity instance) throws IOException {
        AssetManager assMng = instance.getAssets();
        InputStream is = assMng.open("tessdata/eng.traineddata");
        OutputStream os = new FileOutputStream(instance.getFilesDir() + "/tessdata/eng.traineddata");
        byte[] buffer = new byte[1024];
        int read;
        while ((read = is.read(buffer)) != -1) {
            os.write(buffer, 0, read);
        }

        is.close();
        os.flush();
        os.close();
    }
}
