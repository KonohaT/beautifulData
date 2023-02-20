package src.processing;
import processing.core.PApplet;
import java.lang.Math;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ProcessingTest4 extends PApplet { //this test adds weights outside 0 - 1, with orange being negative and blue being positive
    int w = 1600;
    int h = 1000;

    int rowCount = 4;
    int[] rowNodes = {16, 10, 10, 4};
    int weightMin = -1;
    int weightMax = 1;


    int maxRowSize = Arrays.stream(rowNodes).max().getAsInt();
    int nodeIncrements = (h - 200) / (maxRowSize - 1);
    int rowIncrements = (w - 200) / (rowCount - 1);

    ArrayList<Node>[] matrix = new ArrayList[rowCount];

    public void settings() {
        size(w, h);

        for (int i = 0; i < rowCount; i++){
            matrix[i] = new ArrayList<Node>();
        }

        int startingX = 100;
        for (int rowPlace = 0; rowPlace < rowCount; rowPlace++){
            ArrayList<Node> row = matrix[rowPlace];
            float nodesInRow = (float) rowNodes[rowPlace];
            float startingY = (h / 2) - (nodeIncrements * ((nodesInRow - 1) / 2));
            System.out.println(startingY);
            System.out.println(nodesInRow);

            for (int nodePlace = 0; nodePlace < nodesInRow; nodePlace++){
                Node node = new Node(startingX, startingY, 15);
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


        //create table for current weights
        //create table for current node values

        for (int rowPlace = 0; rowPlace < rowCount - 1; rowPlace++){ //renders all nodes except last row and adds weight lines between them.
            ArrayList<Node> row = matrix[rowPlace];
            ArrayList<Node> nextRow = matrix[rowPlace + 1];
            for (Node node : row){
                node.setValue((float) Math.random());
                node.render(this);
                for (Node nextNode : nextRow){
                    float weight = ThreadLocalRandom.current().nextFloat(weightMin, weightMax);
                    System.out.println(weight);
                    weightBezier(node, nextNode, weight);
                }
            }
        }
        for (Node node : matrix[rowCount - 1]){ //renders last row
            node.setValue((float) Math.random());
            node.render(this);
        }

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("X: " + mouseX);
        System.out.println("Y: " + mouseY);
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
            System.out.println("orange");
        }

        float xDif = node1.x - node2.x;
        float yDif = node1.y - node2.y;
        double distance = Math.sqrt((xDif * xDif) + (yDif * yDif));

        float smallYMod = Math.signum(node1.y - node2.y); //1 if node1 below node2, otherwise -1

        float controlSmallX = (float) (node1.x + distance / 4);
        float controlSmallY = (float) (node1.y + (smallYMod * distance / 4));

        bezier(node1.x, node1.y, controlSmallX, controlSmallY, node1.x, node2.y, node2.x, node2.y);

    }



    //public ArrayList<Node>[] retrieveWeights(int epoch) {}
        //retrieve values

        //format into ArrayList


    public static void main(String[] args) {

        ProcessingTest4 pt = new ProcessingTest4();
        PApplet.runSketch(new String[]{"ProcessingTest4"}, pt);
    }
}
