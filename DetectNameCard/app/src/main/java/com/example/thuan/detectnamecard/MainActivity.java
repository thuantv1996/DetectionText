package com.example.thuan.detectnamecard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.util.Log;

import Core.Block;
import Core.Text;
import Ocr.IOcrService;
import Ocr.TesseractOcr;
import ProcessImage.IProcessImage;
import ProcessImage.ImageScan;
import TextDetection.IDetectionText;
import TextDetection.TextDetection;


import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    IOcrService tesseract;
    Button btnTest;
    ImageView imgTest;
    Bitmap input;
    IProcessImage processImage;
    IDetectionText textDetection;

    private static final String TAG = "OCVSample::Activity";

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i(TAG, "OpenCV loaded successfully");
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTest = (Button) findViewById(R.id.btnTest);
        imgTest = (ImageView) findViewById(R.id.imgTest);
        try {
            tesseract = new TesseractOcr();
            tesseract.copyFile(this);
            tesseract.create(this);
        } catch (Exception e) {
        }
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickTest();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_3, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    private void ClickTest() {
        input = BitmapFactory.decodeResource(getResources(), R.drawable.card2);
        processImage = new ImageScan();
        input = processImage.processImageScan(input);
        imgTest.setImageBitmap(input);
        textDetection = new TextDetection();
        Text text = textDetection.getText(tesseract.getContent(input),tesseract.getInfo(input),input.getWidth(),input.getHeight());
        int i=0;
    }
}
