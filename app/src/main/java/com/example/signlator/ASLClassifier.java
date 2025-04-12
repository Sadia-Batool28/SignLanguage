package com.example.signlator;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.common.ops.NormalizeOp;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.image.ops.ResizeOp;
import org.tensorflow.lite.support.image.ops.ResizeWithCropOrPadOp;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;

public class ASLClassifier {
    private static final String MODEL_NAME = "asl_cnn_model.tflite";
    private static final int INPUT_SIZE = 224; // Adjust based on your model's input size
    private static final float IMAGE_MEAN = 0.0f;
    private static final float IMAGE_STD = 255.0f;

    private final Context context;
    private org.tensorflow.lite.Interpreter tflite;
    private TensorImage inputImageBuffer;

    public ASLClassifier(Context context) {
        this.context = context;
    }

    public void init() throws IOException {
        tflite = new org.tensorflow.lite.Interpreter(loadModelFile(context));

        // Create input tensor
        inputImageBuffer = new TensorImage(DataType.FLOAT32);

        Log.d("ASLClassifier", "Model loaded successfully");
    }

    public String classify(Bitmap bitmap) {
        if (tflite == null) {
            Log.e("ASLClassifier", "Classifier has not been initialized");
            return "Model not loaded";
        }

        // Preprocess the image
        ImageProcessor imageProcessor = new ImageProcessor.Builder()
                .add(new ResizeWithCropOrPadOp(INPUT_SIZE, INPUT_SIZE))
                .add(new ResizeOp(INPUT_SIZE, INPUT_SIZE, ResizeOp.ResizeMethod.BILINEAR))
                .add(new NormalizeOp(IMAGE_MEAN, IMAGE_STD))
                .build();

        inputImageBuffer.load(bitmap);
        inputImageBuffer = imageProcessor.process(inputImageBuffer);

        // Run inference
        TensorBuffer outputBuffer = TensorBuffer.createFixedSize(
                new int[]{1, 26}, DataType.FLOAT32); // Adjust output size based on your model

        tflite.run(inputImageBuffer.getBuffer(), outputBuffer.getBuffer());

        // Get the prediction
        float[] probabilities = outputBuffer.getFloatArray();
        int predictedClass = argmax(probabilities);

        // Convert class index to letter (A-Z)
        char predictedLetter = (char) ('A' + predictedClass);

        return String.valueOf(predictedLetter);
    }

    private int argmax(float[] array) {
        int maxIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private java.nio.MappedByteBuffer loadModelFile(Context context) throws IOException {
        android.content.res.AssetFileDescriptor fileDescriptor = context.getAssets().openFd(MODEL_NAME);
        java.io.FileInputStream inputStream = new java.io.FileInputStream(fileDescriptor.getFileDescriptor());
        java.nio.channels.FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(java.nio.channels.FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    public void close() {
        if (tflite != null) {
            tflite.close();
            tflite = null;
        }
    }
}