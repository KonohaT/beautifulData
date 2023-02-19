package src.processing;
import processing.core.PApplet;
import java.lang.Math;
public class ProcessingTemplate extends PApplet {
    int w = 800;
    int h = 800;

    public void settings() {
        size(w, h);
    }

    public void draw() {

    }

    public static void main(String[] args) {
        ProcessingTest pt = new ProcessingTest();
        PApplet.runSketch(new String[]{"ProcessingTest"}, pt);
    }
}
