package src.processing;
import processing.core.PApplet;
import java.lang.Math;
import java.util.concurrent.TimeUnit;

public class ProcessingTest2 extends PApplet {
    int w = 800;
    int h = 800;

    int nodeCount = 5;

    Node[] firstRow = new Node[nodeCount];
    Node[] secondRow = new Node[nodeCount];
    Node[]thirdRow = new Node[nodeCount];


    public void settings() {
        size(w, h);
    }

    public void draw() {
        background(255);
        ellipseMode(CENTER);
        int nodeIncrement = 600 / nodeCount;
        for (int i = 0; i < nodeCount; i++){
            firstRow[i] = new Node(200, i * nodeIncrement + 100, 10);
            secondRow[i] = new Node(400, i * nodeIncrement + 100, 10);
            thirdRow[i] = new Node(600, i * nodeIncrement + 100, 10);


            firstRow[i].render(this);
            secondRow[i].render(this);
            thirdRow[i].render(this);
        }



        for (Node first : firstRow){
            for (Node next : secondRow){
                float weight = (float) Math.random();
                weightBezier(first, next, weight);
            }
        }

        for (Node second : secondRow){
            for (Node third : thirdRow){
                float weight = (float) Math.random();
                weightBezier(second, third, weight);
            }
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void weightBezier(Node node1, Node node2, float weight){
        noFill();
        float blue = 255 - (weight * 255);
        float red = weight * 255;
        stroke(red);
        float xDif = node1.x - node2.x;
        float yDif = node1.y - node2.y;
        double distance = Math.sqrt((xDif * xDif) + (yDif * yDif));

        float smallYMod = Math.signum(node1.y - node2.y); //1 if node1 below node2, otherwise -1

        float controlSmallX = (float) (node1.x + distance / 4);
        float controlSmallY = (float) (node1.y + (smallYMod * distance / 4));

        bezier(node1.x, node1.y, controlSmallX, controlSmallY, node1.x, node2.y, node2.x, node2.y);

    }

    public static void main(String[] args) {
        ProcessingTest2 pt = new ProcessingTest2();
        PApplet.runSketch(new String[]{"ProcessingTest2"}, pt);
    }
}
