package src.processing;
import processing.core.PApplet;
import java.lang.Math;

import java.io.*;


public class ProcessingTest extends PApplet{
    int w = 1000;
    int h = 1000;


    String weightPath = "beautifulData/Project/NetworkData/weights.csv";
    String nodePath = "beautifulData/Project/NetworkData/nodePriority.csv";
    File weightsCSV = new File(weightPath);
    File nodeCSV = new File(nodePath);


    static BufferedReader weightsReader;
    BufferedReader nodeReader;

    {
        try {
            weightsReader = new BufferedReader(new FileReader(weightsCSV));
            nodeReader = new BufferedReader(new FileReader(nodeCSV));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void settings() {
        size(w, h);
    }

    @Override
    public void draw() {
        background(20);
        fill(153, 196, 230);
        stroke(60);
        ellipseMode(CENTER); // ref. point to ellipse is its center
        for (int i = 400; i > 50; i -= 50){
            drawRing(20, w/2, h/2, i);
        }

    }

    public void drawRing(int nodeCount, int centerX, int centerY, int radius){
        double twoPi = Math.toRadians(360);
        double step = twoPi / nodeCount;

        for (double rad = 0; rad < twoPi; rad += step){
            float xPos = (float) Math.cos(rad) * radius + centerX;
            float yPos = (float) Math.sin(rad) * radius + centerY;
            float weight = 0.6f;

            Node node = new Node(xPos, yPos, 10);
            node.setWeight(weight);
            node.render(this);
        }
    }

    public static void main (String[] args) {
        ProcessingTest pt = new ProcessingTest();
        PApplet.runSketch(new String[]{"ProcessingTest"}, pt);




        /*
        boolean hasNext = true;
        String nextWeights;
        while (hasNext){
            try {
                nextWeights = weightsReader.readLine();
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }
            System.out.println(nextWeights);
            if (nextWeights == null){
                hasNext = false;
            }

        }
        */
    }
}
