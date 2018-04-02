package MachineLearning;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.TermCriteria;
import org.opencv.ml.CvSVM;
import org.opencv.ml.CvSVMParams;

import MachineLearning.ISVM;

/**
 * Created by thuan on 3/30/2018.
 */

public class SVM implements ISVM {
    CvSVM svm;
    CvSVMParams params;

    public SVM() {
    }

    @Override
    public void create() {
        svm = new CvSVM();
        params = new CvSVMParams();
        params.set_svm_type(CvSVM.C_SVC);
        params.set_kernel_type(CvSVM.LINEAR);
        params.set_term_crit(new TermCriteria(1, 100, 1e-6));
    }

    @Override
    public void train(Mat trainData, Mat labels) {
        svm.train(trainData, labels, new Mat(), new Mat(), params);
    }

    @Override
    public float predict(Mat input) {
        return svm.predict(input);
    }

    @Override
    public CvSVM load(String path) {
        svm.load(path);
        return svm;
    }

    @Override
    public void save(String path) {
        svm.save(path);
    }
}
