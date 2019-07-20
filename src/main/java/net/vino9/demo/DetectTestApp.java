package net.vino9.demo;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;

import java.util.List;


public class DetectTestApp {

    public static void main(String[] args) throws Exception {
        runDetectTextOnImage("vino9-test-bucket", "check2.png");
    }

    public static void runDetectTextOnImage(String bucket, String imageFile) {
        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();


        DetectTextRequest request = new DetectTextRequest()
                .withImage(new Image()
                        .withS3Object(new S3Object()
                                .withName(imageFile)
                                .withBucket(bucket)));


        try {
            DetectTextResult result = rekognitionClient.detectText(request);
            List<TextDetection> textDetections = result.getTextDetections();

            System.out.println("Detected lines and words for " + imageFile);
            for (TextDetection text : textDetections) {

                System.out.println("Detected: " + text.getDetectedText());
                System.out.println("Confidence: " + text.getConfidence().toString());
                System.out.println("Id : " + text.getId());
                System.out.println("Parent Id: " + text.getParentId());
                System.out.println("Type: " + text.getType());
                System.out.println();
            }
        } catch (AmazonRekognitionException e) {
            e.printStackTrace();
        }
    }
}