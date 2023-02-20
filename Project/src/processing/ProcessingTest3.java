package src.processing;
import processing.core.PApplet;
import java.lang.Math;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ProcessingTest3 extends PApplet { //this test adds rows of different sizes and creates a ArrayList matrix for all nodes
    int w = 1600;
    int h = 1000;

    int rowCount = 4;
    int[] rowNodes = {64, 20, 20 , 10};


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
        background(255);
        //background(0) //uncomment these several for inverted colors
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
                    float weight = (float) Math.random();
                    weightBezier(node, nextNode, weight);
                }
            }
        }
        for (Node node : matrix[rowCount - 1]){ //renders last row
            node.setValue((float) Math.random());
            node.render(this);
        }

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("X: " + mouseX);
        System.out.println("Y: " + mouseY);
    }

    public void weightBezier(Node node1, Node node2, float weight){
        noFill();
        strokeWeight(1);
        float darkness = 255 - (weight * 255);
        //float lightness = weight * 255;
        //stroke(lightness);
        stroke(darkness);
        float xDif = node1.x - node2.x;
        float yDif = node1.y - node2.y;
        double distance = Math.sqrt((xDif * xDif) + (yDif * yDif));

        float smallYMod = Math.signum(node1.y - node2.y); //1 if node1 below node2, otherwise -1

        float controlSmallX = (float) (node1.x + distance / 4);
        float controlSmallY = (float) (node1.y + (smallYMod * distance / 4));

        bezier(node1.x, node1.y, controlSmallX, controlSmallY, node1.x, node2.y, node2.x, node2.y);

    }

    public static void main(String[] args) {


        ProcessingTest3 pt = new ProcessingTest3();
        PApplet.runSketch(new String[]{"ProcessingTest3"}, pt);
    }
}
