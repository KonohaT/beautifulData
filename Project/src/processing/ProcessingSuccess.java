package src.processing;
import processing.core.PApplet; //To use Processing, import org.processing:core.3.3.7 from Maven

import java.io.*;
import java.lang.Math;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ProcessingSuccess extends PApplet { //this test adds weight input
    int w = 1600;
    int h = 1000;

    int rowCount = 3;
    int[] rowNodes = {64, 50, 10};
    int weightMin = -1;
    int weightMax = 1;

    int filePlace = 0;


    int maxRowSize = Arrays.stream(rowNodes).max().getAsInt();
    int nodeIncrements = (h - 200) / (maxRowSize - 1);
    int rowIncrements = (w - 200) / (rowNodes.length - 1);

    ArrayList<Node>[] matrix = new ArrayList[rowNodes.length];

    public void settings() {
        size(w, h);

        for (int i = 0; i < rowNodes.length; i++){
            matrix[i] = new ArrayList<Node>();
        }

        int startingX = 100;
        for (int rowPlace = 0; rowPlace < rowNodes.length; rowPlace++){
            ArrayList<Node> row = matrix[rowPlace];
            float nodesInRow = (float) rowNodes[rowPlace];
            float startingY = (h / 2) - (nodeIncrements * ((nodesInRow - 1) / 2));


            for (int nodePlace = 0; nodePlace < nodesInRow; nodePlace++){
                Node node = new Node(startingX, startingY, 8);
                row.add(node);

                startingY += nodeIncrements;
            }
            startingX += rowIncrements;

        }
        System.out.println("got here");



    }

    public void draw() {
        //background(255);
        background(0); //uncomment these several for inverted colors
        ellipseMode(CENTER);

        String unfinishedPath = "beautifulData/Project/NetworkData/weights_epoch_";
        String finishedPath = unfinishedPath + filePlace + ".csv";

        File weights = new File(finishedPath);
        while (!weights.exists()){
            weights = new File(finishedPath);

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        long startTime = System.currentTimeMillis();
        BufferedReader oneReader = weightsReader(weights);
        try {
            oneReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int rowPlace = 1; rowPlace < rowNodes.length; rowPlace++){ //renders all nodes except last row and adds weight lines between them.
            ArrayList<Node> row = matrix[rowPlace];
            ArrayList<Node> prevRow = matrix[rowPlace - 1];
            for (Node node : row){
                for (Node prevNode : prevRow){
                    float weight = retrieveWeight(oneReader);
                    //System.out.println("Weight: " + weight);
                    weightBezier(prevNode, node, weight);
                }
                //node.setValue((float) Math.random());
                node.render(this);
            }
        }
        for (Node node : matrix[0]){ //renders first row
            //node.setValue((float) Math.random());
            node.render(this);
        }
        //saveFrame("frame#####.png");
        // creates a png of each epoch starting at 1. I used ImageMagick to combine these into a gif. command: convert -delay 30 *.png +repage -loop 0 learning.gif
        long endTime = System.currentTimeMillis();
        long totalMillis = (endTime - startTime);
        System.out.println("Epoch " + filePlace + " took " + totalMillis + " milliseconds");

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        filePlace += 1;
    }

    public void weightBezier(Node node1, Node node2, float weight){ //weight should be between weightMin and weightMax
        noFill();
        strokeWeight(1);
        /*
        float darkness = 255 - (weight * 255);
        float lightness = weight * 255;
        stroke(lightness);
        stroke(darkness);
        */

        if (weight > 0){ //setting blue color
            float blueProportion = Math.abs(weight / weightMax) * 15; //between 0 and 15
            int[] dodgerRGB = {2, 10, 17};
            stroke(dodgerRGB[0] * blueProportion, dodgerRGB[1] * blueProportion, dodgerRGB[1] * blueProportion);
        }
        else { //setting orange color
            float orangeProportion = Math.abs(weight / weightMin) * 15; //between 0 and 15
            int[] tomatoRGB = {17, 7, 5};
            stroke(tomatoRGB[0] * orangeProportion, tomatoRGB[1] * orangeProportion, tomatoRGB[2] * orangeProportion);
        }

        float xDif = node1.x - node2.x;
        float yDif = node1.y - node2.y;
        double distance = Math.sqrt((xDif * xDif) + (yDif * yDif));

        float smallYMod = Math.signum(node1.y - node2.y); //1 if node1 below node2, otherwise -1

        float controlSmallX = (float) (node1.x + distance / 4);
        float controlSmallY = (float) (node1.y + (smallYMod * distance / 4));

        bezier(node1.x, node1.y, controlSmallX, controlSmallY, node1.x, node2.y, node2.x, node2.y);

    }



    private BufferedReader weightsReader(File weights) { //remove static after testing

        BufferedReader weightsReader;
        try {
            weightsReader = new BufferedReader(new FileReader(weights));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return weightsReader;
    }

    private float retrieveWeight(BufferedReader reader){
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {throw new RuntimeException(e);}
        String weight = line.split(",")[0];
        return Float.parseFloat(weight);
    }
    /*
    public static void formatWeights(int epoch) throws IOException { //remove static after testing
        File weightsFile = retrieveWeights(epoch);
        BufferedReader weightsReader;
        try {
            weightsReader = new BufferedReader(new FileReader(weightsFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String line = weightsReader.readLine();
        while ((line = weightsReader.readLine()) != null){
            System.out.println(line);
        }
    }
    */

    public static void main(String[] args) {
        ProcessingSuccess pt = new ProcessingSuccess();
        PApplet.runSketch(new String[]{"ProcessingSuccess"}, pt);
    }
}
