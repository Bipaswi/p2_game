import processing.core.PApplet;
import processing.core.PVector;


// A representation of a kinematic character
final class Character {

    final float width = 600;
    final float height = 600;
    PApplet applet;

    // I'm allowing public access to keep things snappy
    // Static Data
    public PVector position;
    public float orientation;
    // Kinematic Data

    public PVector velocity;

    Character(int x, int y, float or,
              float xVel, float yVel, PApplet applet) {
        position = new PVector(x, y);
        orientation = or;
        velocity = new PVector(xVel, yVel);
        this.applet = applet;
    }

    void updatePlayer() {
        position.add(velocity);
    }

    void confinePlayer() {
        position.x = applet.constrain(position.x, 15, width -15);
        position.y = applet.constrain(position.y, 15, height-15);
    }

    void displayPlayer() {
        applet.fill(255, 0, 0);
        applet.ellipse(position.x, position.y, 30, 30);
    }
}