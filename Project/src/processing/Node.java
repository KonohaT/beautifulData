package src.processing;
import processing.core.PApplet;

public class Node {
    private float weight; //should be between 0 and 1
    public float x;
    public float y;
    public int radius;

    public Node(float x, float y, int radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public float getWeight(){
        return weight;
    }

    public void setWeight(float input){
        weight = input;
    }

    public void setPos(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void render(PApplet sketch){
        int blue = (int) (weight * 256);
        sketch.fill(0, 0, blue);
        sketch.ellipse(x, y, radius, radius);
    }
}
