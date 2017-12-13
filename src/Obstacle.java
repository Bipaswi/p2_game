import processing.core.PVector;
import processing.core.PApplet;

public class Obstacle {

    PVector position;
    public PApplet applet;
    int size;

    public Obstacle(float x, float y, int size, PApplet applet) {
        this.position = new PVector(x,y);
        this.size = size;
        this.applet = applet;
    }

    public void display(PVector camera){
        applet.fill(235, 255, 20);
        applet.rect(position.x - camera.x, position.y - camera.y, size, size);
    }

}
