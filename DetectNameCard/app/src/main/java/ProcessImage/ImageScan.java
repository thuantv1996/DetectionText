package ProcessImage;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * Created by thuan on 3/29/2018.
 */

public class ImageScan implements IProcessImage {
    @Override
    public Bitmap processImageScan(Bitmap img) {
        return beforScan(img);
    }

    private Bitmap beforScan(Bitmap img) {
        Mat gray = new Mat(img.getWidth(), img.getHeight(), CvType.CV_8UC1);
        Utils.bitmapToMat(img, gray);
        Imgproc.cvtColor(gray, gray, Imgproc.COLOR_RGB2GRAY);
        Mat des = new Mat(img.getWidth(), img.getHeight(), CvType.CV_8UC1);
        Imgproc.threshold(gray, des, 127, 255, Imgproc.THRESH_OTSU);
        Bitmap res = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(des, res);
        return res;
    }

}
