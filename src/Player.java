import processing.core.PApplet;
import processing.core.PVector;

import static processing.core.PConstants.PI;


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
    public PVector frontWheel;
    //PVector carLocation;
    float carHeading;
    float carSpeed, maxSpeed;
    float steerAngle;
    float maxSteerAngle = PI/4;

    boolean plus, minus, up, down, left, right, steerLock;

    Player(int x, int y, int size, PApplet applet) {
        position = new PVector(x, y);
        this.velocity = new PVector(0, 0);
        this.size = size;
        this.applet = applet;
    }

    // need to add physics
    // acceleration
    // left and right

    public void updatePlayer() {
        position.add(velocity);
    }

    public void displayPlayer(int r, int g, int b, PVector camera) {
        applet.fill(r, g, b);
        applet.rect(position.x - camera.x, position.y - camera.y, size, size);
    }
}