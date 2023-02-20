package src.processing;
import processing.core.PApplet;

public class Node {
    private float value = 1; //should be between 0 and 1
    public float x;
    public float y;
    public int radius;

    public Node(float x, float y, int radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public float getValue(){
        return value;
    }

    public void setValue(float input){

        value = input;
    }

    public void setPos(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void render(PApplet sketch){
        int brightness = (int) (value * 256);
        int darkness = ((int) (255 - value * 256));
        sketch.strokeWeight(0);
        sketch.fill(brightness); //switch to brightness for dark mode
        sketch.ellipse(x, y, radius, radius);
    }
}
