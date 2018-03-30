package TextDetection;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

import TextDetection.IDetectionText;
import Core.MyRect;
import Core.Block;
import Core.Text;
import Core.Line;
import Core.Character;
import Core.Word;

/**
 * Created by thuan on 3/29/2018.
 */

public class TextDetection implements IDetectionText {
    public TextDetection() {
    }

    @Override
    public List<org.opencv.core.Rect> rectDetectText(Mat img) {

        List<org.opencv.core.Rect> boundRect = new ArrayList<>();

        Mat img_gray = new Mat(), img_sobel = new Mat(), img_threshold = new Mat(), element = new Mat();
        Imgproc.cvtColor(img, img_gray, Imgproc.COLOR_RGB2GRAY);
        Imgproc.Sobel(img_gray, img_sobel, CvType.CV_8U, 1, 0, 3, 1, 0, Imgproc.BORDER_DEFAULT);
        //at src, Mat dst, double thresh, double maxval, int type
        Imgproc.threshold(img_sobel, img_threshold, 0, 255, 8);
        element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(img.width() / 15, img.height() / 25));
        Imgproc.morphologyEx(img_threshold, img_threshold, Imgproc.MORPH_CLOSE, element);
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(img_threshold, contours, hierarchy, 0, 1);

        List<MatOfPoint> contours_poly = new ArrayList<MatOfPoint>(contours.size());

        for (int i = 0; i < contours.size(); i++) {

            MatOfPoint2f mMOP2f1 = new MatOfPoint2f();
            MatOfPoint2f mMOP2f2 = new MatOfPoint2f();

            contours.get(i).convertTo(mMOP2f1, CvType.CV_32FC2);
            Imgproc.approxPolyDP(mMOP2f1, mMOP2f2, 2, true);
            mMOP2f2.convertTo(contours.get(i), CvType.CV_32S);

            org.opencv.core.Rect appRect = Imgproc.boundingRect(contours.get(i));
            if (appRect.width > appRect.height) {
                boundRect.add(appRect);
            }
        }
        return boundRect;
    }

    @Override
    public List<Block> getListBlockDetect(Bitmap img) {
        Mat mat = new Mat(img.getWidth(), img.getHeight(), CvType.CV_8UC1);
        Utils.bitmapToMat(img, mat);
        List<org.opencv.core.Rect> rects = rectDetectText(mat);
        List<Block> lstBlock = new ArrayList<>();
        for (int i = 0; i < rects.size(); i++) {
            MyRect rect = new MyRect((int) (rects.get(i).tl().y),
                    (int) (rects.get(i).tl().x),
                    (int) (rects.get(i).br().y),
                    (int) (rects.get(i).br().x));
            // create Bitmap
            Bitmap r = Bitmap.createBitmap(rect.getWidth() + 10, rect.getHeight(), Bitmap.Config.ARGB_8888);
            // create canvas
            Canvas canvas = new Canvas(r);
            // draw on bitmap
            canvas.drawBitmap(img, new android.graphics.Rect(rect.getLeft() - 5, rect.getTop(), rect.getRight() + 5, rect.getBottom()),
                    new android.graphics.Rect(0, 0, r.getWidth(), r.getHeight()), new Paint());
            // create Block
            Block block = new Block(r, rect);
            lstBlock.add(block);
        }
        return lstBlock;
    }

    @Override
    public Text getText(String str, String[] info, int w, int h) {
        String[] data = str.split("\n");
        Text texts = new Text();
        Line lines = new Line();
        Word words = new Word();
        int cur = 0;
        for (int i = 0; i < data.length; i++) {
            lines.setContent(data[i]);
            for (int j = 0; j < data[i].length(); j++) {
                if (data[i].charAt(j) == ' ') {

                    if(lines.getPosition().getLeft()==0)
                    {
                        lines.getPosition().setLeft(words.getPosition().getLeft());
                    }
                    else
                    {
                        if (words.getPosition().getLeft() < lines.getPosition().getLeft())
                            lines.getPosition().setLeft(words.getPosition().getLeft());
                    }

                    if (words.getPosition().getRight() > lines.getPosition().getRight())
                        lines.getPosition().setRight(words.getPosition().getRight());

                    if(lines.getPosition().getTop()==0)
                    {
                        lines.getPosition().setTop(words.getPosition().getTop());
                    }
                    else
                    {
                        if (words.getPosition().getTop() < lines.getPosition().getTop())
                            lines.getPosition().setTop(words.getPosition().getTop());
                    }
                    if (words.getPosition().getBottom() > lines.getPosition().getBottom())
                        lines.getPosition().setBottom(words.getPosition().getBottom());
                    lines.getListWords().add(words);
                    words = new Word();
                    continue;
                } else {
                    String[] dataInfo = info[cur].split(" ");
                    MyRect rect = new MyRect(h - Integer.parseInt(dataInfo[4]),
                            Integer.parseInt(dataInfo[1]),
                            h - Integer.parseInt(dataInfo[2]),
                            Integer.parseInt(dataInfo[3])
                    );
                    Character cha = new Character(dataInfo[0].charAt(0), rect);
                    words.getListCharacter().add(cha);
                    if(words.getPosition().getLeft()==0)
                    {
                        words.getPosition().setLeft(cha.getPosition().getLeft());
                    }
                    else
                    {
                        if (cha.getPosition().getLeft() < words.getPosition().getLeft())
                            words.getPosition().setLeft(cha.getPosition().getLeft());
                    }

                    if (cha.getPosition().getRight() > words.getPosition().getRight())
                        words.getPosition().setRight(cha.getPosition().getRight());

                    if(words.getPosition().getTop()==0)
                    {
                        words.getPosition().setTop(cha.getPosition().getTop());
                    }
                    else
                    {
                        if (cha.getPosition().getTop() < words.getPosition().getTop())
                            words.getPosition().setTop(cha.getPosition().getTop());
                    }
                    if (cha.getPosition().getBottom() > words.getPosition().getBottom())
                        words.getPosition().setBottom(cha.getPosition().getBottom());
                    cur++;
                    continue;
                }
            }
            if(lines.getPosition().getLeft()==0)
            {
                lines.getPosition().setLeft(words.getPosition().getLeft());
            }
            else
            {
                if (words.getPosition().getLeft() < lines.getPosition().getLeft())
                    lines.getPosition().setLeft(words.getPosition().getLeft());
            }

            if (words.getPosition().getRight() > lines.getPosition().getRight())
                lines.getPosition().setRight(words.getPosition().getRight());

            if(lines.getPosition().getTop()==0)
            {
                lines.getPosition().setTop(words.getPosition().getTop());
            }
            else
            {
                if (words.getPosition().getTop() < lines.getPosition().getTop())
                    lines.getPosition().setTop(words.getPosition().getTop());
            }
            if (words.getPosition().getBottom() > lines.getPosition().getBottom())
                lines.getPosition().setBottom(words.getPosition().getBottom());
            lines.getListWords().add(words);
            words = new Word();

            texts.getListLines().add(lines);
            lines = new Line();
        }
        return texts;
    }
}
