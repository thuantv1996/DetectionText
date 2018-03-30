package TextDetection;

import android.graphics.Bitmap;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import java.util.List;

import Core.Block;
import Core.Text;

/**
 * Created by thuan on 3/29/2018.
 */

public interface IDetectionText {
    public List<Rect> rectDetectText(Mat img);
    public List<Block> getListBlockDetect(Bitmap img);
    public Text getText(String str, String[] info,int w,int h);
}
