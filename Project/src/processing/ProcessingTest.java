package src.processing;
import processing.core.PApplet;
//Checking branch merging

public class ProcessingTest extends PApplet{

    @Override
    public void settings() {
        size(200, 200);
    }

    @Override
    public void draw() {
        background(0);
        fill(255, 0, 0);
        ellipse(100, 100, 100, 100);
    }

    public static void main (String[] args) {
        ProcessingTest pt = new ProcessingTest();
        PApplet.runSketch(new String[]{"ProcessingTest"}, pt);
    }
}
