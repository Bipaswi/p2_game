import processing.core.PApplet;
import processing.core.PVector;


// A representation of a kinematic character
final class Player {
    final float width = 600;
    final float height = 600;
    public int size = 30;
//    public int r = 255;
//    public int g = 0;
//    public int b = 0;

    public PVector position;
    public float orientation;
    public PVector velocity;
    public PVector face;
    public PApplet applet;

    Player(int x, int y, float or, float xVel, float yVel, int size, PApplet applet) {
        position = new PVector(x, y);
        orientation = or;
        velocity = new PVector(xVel, yVel);
        this.size = size;
        this.applet = applet;
    }

    public void displayPlayer(int r, int g, int b) {
        // x = 300
        // y = 300
        applet.fill(r, g, b);
        applet.rect(position.x, position.y, size, size);
        //applet.ellipse(position.x, position.y, size,size);
    }
}