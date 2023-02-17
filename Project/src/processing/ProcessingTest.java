package src.processing;
import processing.core.PApplet;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;//Checking branch merging


public class ProcessingTest extends PApplet{
    int w = 600;
    int h = 400;
    int movement = w / 100;
    int y=h/2;
    int x = w/2;

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
        background(60);
        fill(255,255,255); // fill color red
        stroke(0,0,255); // stroke color blue
        ellipseMode(CENTER); // ref. point to ellipse is its center

        ellipse(x, y, 20, 20);
        int randomX = ThreadLocalRandom.current().nextInt(0 - movement, movement);
        int randomY = ThreadLocalRandom.current().nextInt(0 - movement, movement);



        // increment x and y
        x += randomX;
        y += randomY;
    }

    public static void main (String[] args) {
        ProcessingTest pt = new ProcessingTest();
        PApplet.runSketch(new String[]{"ProcessingTest"}, pt);
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
    }
}
