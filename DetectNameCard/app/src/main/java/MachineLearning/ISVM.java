package MachineLearning;

import org.opencv.core.Mat;
import org.opencv.ml.CvSVM;

/**
 * Created by thuan on 3/30/2018.
 */

public interface ISVM {
    public void train(Mat trainData,Mat labels);
    public void create();
    public float predict(Mat input);
    public void save(String path);
    public CvSVM load(String path);
}
