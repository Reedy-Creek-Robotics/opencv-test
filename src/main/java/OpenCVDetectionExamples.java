import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class OpenCVDetectionExamples {
    // quickly grabbed colors for the "pink" range from this site, could use some testing/tweaking
    // https://cvexplained.wordpress.com/2020/04/28/color-detection-hsv/
    static final Scalar LOW_RED = new Scalar(145, 0, 20);
    static final Scalar HIGH_RED = new Scalar(155, 255, 255);

    public static void main(String[] args){
        // load the library
        nu.pattern.OpenCV.loadLocally();

        // we will test 2 files so you can compare: 1 with the TSE and one with no pink at all
        File tseFile = new File("input/tse-pink.jpeg");
        System.out.println(tseFile.getName());
        basicExample(tseFile);
        //inRangeExample(tseFile);
        //blurInRangeExample(tseFile);

        File noPinkFile = new File("input/no-pink.jpeg");
        System.out.println(noPinkFile.getName());
        basicExample(noPinkFile);
        //inRangeExample(noPinkFile);
        //blurInRangeExample(noPinkFile);
    }

    public static void basicExample(File file) {
        Mat input = Imgcodecs.imread(file.getAbsolutePath());
        System.out.println("  basic:");

        // convert the image to HSV, take the mean of the colors, first value is Hue
        Mat hsvMat = new Mat();
        Imgproc.cvtColor(input, hsvMat, Imgproc.COLOR_BGR2HSV);
        System.out.println("    HSV Values:"+Core.mean(hsvMat));
        Imgcodecs.imwrite("output/hsv-" + file.getName(), hsvMat);
    }

    public static void inRangeExample(File file) {
        Mat input = Imgcodecs.imread(file.getAbsolutePath());
        System.out.println("  inRange:");

        // convert the image to HSV, take the mean of the colors, first value is Hue
        Mat hsvMat = new Mat();
        Imgproc.cvtColor(input, hsvMat, Imgproc.COLOR_BGR2HSV);
        System.out.println("    HSV Values:"+Core.mean(hsvMat));
        Imgcodecs.imwrite("output/hsv-convert.jpeg", hsvMat);

        // using the values we set for the pink range, find them all
        Mat inRangeMat = new Mat();
        Core.inRange(hsvMat, LOW_RED, HIGH_RED, inRangeMat);
        System.out.println("    HSV countNonZero Values: "+Core.countNonZero(inRangeMat));
        Imgcodecs.imwrite("output/inrange-" + file.getName(), inRangeMat);
    }

    public static void blurInRangeExample(File file) {
        Mat input = Imgcodecs.imread(file.getAbsolutePath());
        System.out.println("  blurInRange:");

        // blur the image and convert to HSV
        Mat blurredImage = new Mat();
        Mat hsvBlurredMat = new Mat();
        Imgproc.blur(input, blurredImage, input.size());
        Imgproc.cvtColor(blurredImage, hsvBlurredMat, Imgproc.COLOR_BGR2HSV);
        System.out.println("    HSV Blur Values [" + file.getName() + "]:"+ Core.mean(hsvBlurredMat));
        Imgcodecs.imwrite("output/blur-" + file.getName(), hsvBlurredMat);

        // using the values we set for the pink range, find them all
        Mat inRangeMat = new Mat();
        Core.inRange(hsvBlurredMat, LOW_RED, HIGH_RED, inRangeMat);
        System.out.println("    HSV countNonZero Values: "+Core.countNonZero(inRangeMat));
        Imgcodecs.imwrite("output/blur-inrange-" + file.getName(), inRangeMat);
    }
}