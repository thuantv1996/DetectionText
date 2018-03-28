public class detect{
public static List<Rect> detectLetters(Mat img){    
    List<Rect> boundRect=new ArrayList<>();

    Mat img_gray =new Mat(), img_sobel=new Mat(), img_threshold=new Mat(), element=new Mat();
    Imgproc.cvtColor(img, img_gray, Imgproc.COLOR_RGB2GRAY);
    Imgproc.Sobel(img_gray, img_sobel, CvType.CV_8U, 1, 0, 3, 1, 0, Core.BORDER_DEFAULT);
    //at src, Mat dst, double thresh, double maxval, int type
    Imgproc.threshold(img_sobel, img_threshold, 0, 255, 8);
    element=Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(15,5));
    Imgproc.morphologyEx(img_threshold, img_threshold, Imgproc.MORPH_CLOSE, element);
    List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
    Mat hierarchy = new Mat();
    Imgproc.findContours(img_threshold, contours,hierarchy, 0, 1);

    List<MatOfPoint> contours_poly = new ArrayList<MatOfPoint>(contours.size());

     for( int i = 0; i < contours.size(); i++ ){             

         MatOfPoint2f  mMOP2f1=new MatOfPoint2f();
         MatOfPoint2f  mMOP2f2=new MatOfPoint2f();

         contours.get(i).convertTo(mMOP2f1, CvType.CV_32FC2);
         Imgproc.approxPolyDP(mMOP2f1, mMOP2f2, 2, true); 
         mMOP2f2.convertTo(contours.get(i), CvType.CV_32S);


            Rect appRect = Imgproc.boundingRect(contours.get(i));
            if (appRect.width>appRect.height) {
                boundRect.add(appRect);
            }
     }

    return boundRect;
}
}