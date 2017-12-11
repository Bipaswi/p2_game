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
    PVector carLocation;
    float carHeading;
    float carSpeed, maxSpeed;
    float steerAngle;
    float maxSteerAngle = PI/4;
    boolean plus, minus, up, down, left, right, steerLock;



    Player(int x, int y, float or, float xVel, float yVel, int size, PApplet applet) {
        position = new PVector(x, y);
        orientation = or;
        velocity = new PVector(xVel, yVel);
        this.size = size;
        this.applet = applet;
    }

    // need to add physics
    // acceleration
    // left and right



    public void updatePlayer() {
        position.add(velocity);

//        y_change = sin(rotation) * speed;
//        x_change = cos(rotation) * speed;
//
//        car.x += x_change;
//        car.y += y_change;
    }

    public void displayPlayer(int r, int g, int b, PVector camera) {
        // x = 300
        // y = 300
        applet.fill(r, g, b);
        applet.rect(position.x - camera.x, position.y - camera.y, size, size);
        //applet.ellipse(position.x, position.y, size,size);
    }
}